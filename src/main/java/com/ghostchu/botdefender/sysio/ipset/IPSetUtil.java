package com.ghostchu.botdefender.sysio.ipset;

import com.ghostchu.botdefender.sysio.util.ProcessResult;
import com.ghostchu.botdefender.sysio.util.ProcessUtil;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class IPSetUtil {
    public void setup() throws IllegalStateException {
        ProcessResult result;
        result = ProcessUtil.exec("ipset create botdefender hash:ip hashsize 4096 maxelem 1000000 timeout 600");
        if (result.getCode() == 0) {
            log.info("ipset: create 'botdefender' rule set successfully!");
            result = ProcessUtil.exec("iptables -I INPUT -m set --match-set botdefender src -j DROP");
            if (result.getCode() == 0) {
                log.info("iptables: INPUT - hook into ipset 'botdefender' successfully!");
            } else {
                log.error("iptables: INPUT - failed to hook into ipset 'botdefender'. Error: Code={}, Response={}", result.getCode(), result.getResponse());
                throw new IllegalStateException("iptables: INPUT - failed to hook into ipset 'botdefender'. Error: Code=" + result.getCode() + ", Response=" + result.getResponse());
            }
            result = ProcessUtil.exec("iptables -I FORWARD -m set --match-set botdefender src -j DROP");
            if (result.getCode() == 0) {
                log.info("iptables: FORWARD - hook into ipset 'botdefender' successfully!");
            } else {
                log.error("iptabels: FORWARD - hook into ipset 'botdefender' failed. Error: Code={}, Response={}", result.getCode(), result.getResponse());
                throw new IllegalStateException("iptables: FORWARD - hook into ipset 'botdefender' failed. Error: Code=" + result.getCode() + ", Response=" + result.getResponse());
            }
            result = ProcessUtil.exec("iptables -t raw -A PREROUTING -p tcp -m set --match-set botdefender src -j DROP");
            if (result.getCode() == 0) {
                log.info("iptables: PREROUTING - hook into ipset 'botdefender' successfully!");
            } else {
                log.error("iptabels: PREROUTING - hook into ipset 'botdefender' failed. Error: Code={}, Response={}", result.getCode(), result.getResponse());
                throw new IllegalStateException("iptables: FORWARD - hook into ipset 'botdefender' failed. Error: Code=" + result.getCode() + ", Response=" + result.getResponse());
            }
        } else if (!result.getResponse().contains("already exists")) {
            log.error("ipset: failed to create the 'botdefender' rule set. Error: Code={}, Response={}", result.getCode(), result.getResponse());
            throw new IllegalStateException("ipset: failed to create the 'botdefender' rule set. Error: Code=" + result.getCode() + ", Response=" + result.getResponse());
        } else {
            log.info("ipset: 'botdefender' has been created, skipping...");
        }

        result = ProcessUtil.exec("ipset flush botdefender");
        if (result.getCode() == 0) {
            log.info("ipset: flush 'botdefender' successfully!");
        } else {
            log.warn("ipset: cannot flush 'botdefender' rule set, old ips may cannot be purged. Error: Code={}, Response={}", result.getCode(), result.getResponse());
        }
    }

    public void add(String ip, long duration) {
        // timeout - ms to s
        long sec = duration / 1000;
        if (sec <= 0)
            sec = 1;
        ProcessResult result = ProcessUtil.exec("ipset add botdefender " + ip + " timeout " + sec);
        if (result.getCode() != 0) {
            if (!result.getResponse().contains("already added")) {
                log.warn("ipset: Failed to add ip '{}' to 'botdefender' rule set. Error: Code={}, Response={}", ip, result.getCode(), result.getResponse());
            }
        }
    }

    public void remove(String ip) {
        ProcessResult result = ProcessUtil.exec("ipset del botdefender " + ip);
        if (result.getCode() != 0) {
            if (!result.getResponse().contains("not added")) {
                log.warn("ipset: Failed to remove ip '{}' from 'botdefender' rule set. Error: Code={}, Response={}", ip, result.getCode(), result.getResponse());
            }
        }
    }

    public void flush() {
        ProcessResult result = ProcessUtil.exec("ipset flush botdefender");
        if (result.getCode() != 0) {
            log.warn("ipset: failed to flush rule set 'botdefender'. Error: Code={}, Response={}", result.getCode(), result.getResponse());
        }
    }
}

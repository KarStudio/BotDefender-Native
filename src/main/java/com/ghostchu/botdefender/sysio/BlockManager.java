package com.ghostchu.botdefender.sysio;

import com.ghostchu.botdefender.sysio.ipset.IPSetUtil;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
@Log4j2
public class BlockManager {
    private final IPSetUtil ipSetUtil = new IPSetUtil();

    public BlockManager(){

    }

    public boolean blockIp(@NotNull String ip, long endTime) {
        ipSetUtil.add(ip, endTime);
        return true;
    }

    public boolean unBlockIp(@NotNull String ip) {
        ipSetUtil.remove(ip);
        return true;
    }

}

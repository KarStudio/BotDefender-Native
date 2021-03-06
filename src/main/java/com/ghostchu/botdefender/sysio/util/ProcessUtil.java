package com.ghostchu.botdefender.sysio.util;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

@Log4j2
public class ProcessUtil {
    @SneakyThrows
    @NotNull
    public static ProcessResult exec(String shell) {
        try {
            StringTokenizer st = new StringTokenizer(shell);
            String[] cmdarray = new String[st.countTokens()];
            for (int i = 0; st.hasMoreTokens(); i++)
                cmdarray[i] = st.nextToken();
            Process pr = new ProcessBuilder(cmdarray)
                    .redirectErrorStream(true)
                    .start();
            pr.waitFor(5, TimeUnit.SECONDS);
            if (pr.isAlive()) {
                log.warn("Process timed out: {}, giving up and killing...", shell);
                pr.destroy();
                return new ProcessResult(-1, "Process timed out");
            }
            int code = pr.exitValue();
            BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = buf.readLine()) != null) {
                builder.append(line).append("\n");
            }
            return new ProcessResult(code, builder.toString());
        } catch (Exception exception) {
            log.warn("ProcessUtil.exec error: {}", exception.getMessage());
            return new ProcessResult(-1, "Process exception: " + exception.getMessage());
        }
    }
}

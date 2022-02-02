package com.ghostchu.botdefender.sysio;

import com.ghostchu.botdefender.sysio.ipset.IPSetUtil;
import com.ghostchu.botdefender.sysio.rpc.RPCServer;
import com.ghostchu.botdefender.sysio.util.TimeUtil;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.jline.reader.*;
import org.jline.reader.impl.completer.AggregateCompleter;
import org.jline.reader.impl.completer.ArgumentCompleter;
import org.jline.reader.impl.completer.NullCompleter;
import org.jline.reader.impl.completer.StringsCompleter;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@Log4j2
public class Main {
    private final IPSetUtil ipSetUtil = new IPSetUtil();
    private final YamlConfigurationLoader loader = YamlConfigurationLoader.builder()
            .path(Path.of("config.yml")) // Set where we will load and save to
            .build();
    private final BlockManager blockManager = new BlockManager();
    private RPCServer rpcServer;
    private short rpcPort = 4343;

    @SneakyThrows
    public Main() {
        log.info("BotDefender Native by Ghost_chu for KarNetwork");

        if (System.getProperty("port") != null) {
            rpcPort = Short.parseShort(System.getProperty("port"));
            log.info("RPC port set to {} by system property", rpcPort);
        }

        log.info("This binary designed for unix like system and require ipset & iptables installed.");
        try {
            checkOS();
            checkIpset();
            checkIptables();
            ipSetUtil.setup();
        } catch (IOException | InterruptedException e) {
            log.error("Error occurred while checking requirement.", e);
            System.exit(-1);
        }
        log.info("Starting up RPC server...");
        try {
            this.rpcServer = new RPCServer(this, rpcPort);
            log.info("RPC server is started, Listening on {}.", rpcPort);
        } catch (IOException e) {
            log.error("Failed to start RPC server", e);
            System.exit(-1);
        }
        log.info("Starting up command listener...");
        Completer blockCompleter = new ArgumentCompleter(
                new StringsCompleter("ban", "block", "jail", "add", "create", "new"),
                new StringsCompleter("<ip>"),
                new StringsCompleter("<duration>"),
                NullCompleter.INSTANCE
        );
        Completer unblockCompleter = new ArgumentCompleter(
                new StringsCompleter("unban", "unblock", "pardon", "unjail", "remove", "del", "delete", "rm"),
                new StringsCompleter("<ip>"),
                NullCompleter.INSTANCE
        );
        Completer shutdownCompleter = new ArgumentCompleter(
                new StringsCompleter("stop", "close", "shutdown", "end", "exit", "eof"),
                NullCompleter.INSTANCE
        );
        Completer bdnCompleter = new AggregateCompleter(
                blockCompleter,
                unblockCompleter,
                shutdownCompleter
        );
        Terminal terminal = TerminalBuilder.builder()
                .system(true)
                .build();
        LineReader lineReader = LineReaderBuilder.builder()
                .terminal(terminal)
                .completer(bdnCompleter)
                .build();

        while (true) {
            String line;
            try {
                line = lineReader.readLine();
                executeCommand(line);
            } catch (UserInterruptException e) {
                // Do nothing
            } catch (EndOfFileException e) {
                executeCommand("stop");
                return;
            } catch (Exception e) {
                log.error("Error occurred while executing command.", e);
            }
        }
    }

    public static void main(String[] args) {
        new Main();
    }

    private void executeCommand(String cmd) {
        String[] commandArray = cmd.split(" ");
        String command = commandArray[0];
        String[] commandArgs = new String[commandArray.length];
        System.arraycopy(commandArray, 1, commandArgs, 0, commandArray.length - 1);
        switch (command.toLowerCase(Locale.ROOT)) {
            case "stop", "shutdown", "close", "end", "exit" -> cmdShutdown();
            case "block", "ban", "jail", "add", "create", "new" -> cmdBlock(commandArgs);
            case "unblock", "unban", "pardon", "unjail", "remove", "del", "delete", "rm" -> cmdUnblock(commandArgs);
            default -> {
                log.warn("Unknown command: " + command);
                log.info("Available commands: stop, block, unblock. Press TAB to check all commands");
            }
        }
    }

    private void cmdBlock(@NotNull String[] args) {
        if (args.length < 2) {
            log.error("Invalid command. Usage: block <ip> <time>");
        }
        log.info("ip={}, time={}", args[0], args[1]);
        String ip = args[0];
        long time = TimeUtil.convert(args[1]);
        if (blockIp(ip, time)) {
            log.info("Blocking " + ip + " for " + TimeUtil.convert(time));
        } else {
            log.warn("IP " + ip + " already existed, you must unblock this ip before add new block.");
        }
    }

    private void cmdUnblock(@NotNull String[] args) {
        if (args.length < 1) {
            log.error("Invalid command. Usage: unblock <ip>");
        }
        String ip = args[0];
        if (unblockIp(ip)) {
            log.info("Unblocking " + ip);
        } else {
            log.warn("IP " + ip + " hadn't blocked yet.");
        }
    }

    private void cmdShutdown() {
        log.info("Shutting down...");
        log.info("Closing RPC server...");
        this.rpcServer.stop();
        System.exit(0);
    }

    public void checkOS() {
        log.info("Checking operating system...");
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            log.error("This program is not designed for windows. We only support Unix-like system.");
            System.exit(0);
        }
        log.info("Operating system is OK.");
    }

    public void checkIptables() throws IOException, InterruptedException {
        log.info("Checking iptables...");
        if (!new ProcessBuilder("iptables", "-V").start().waitFor(1, TimeUnit.SECONDS)) {
            log.error("iptables is not installed.");
            log.error("Please install iptables.");
            System.exit(0);
        }
        log.info("iptables is OK.");
    }

    public void checkIpset() throws IOException, InterruptedException {
        log.info("Checking ipset...");
        if (!new ProcessBuilder("ipset", "-V").start().waitFor(1, TimeUnit.SECONDS)) {
            log.error("ipset is not installed.");
            log.error("Please install ipset.");
            System.exit(0);
        }
        log.info("ipset is OK.");
    }

    public boolean blockIp(@NotNull String ip, long duration) {
        return blockManager.blockIp(ip, duration);
    }

    public boolean unblockIp(@NotNull String ip) {
        return blockManager.unBlockIp(ip);
    }


}

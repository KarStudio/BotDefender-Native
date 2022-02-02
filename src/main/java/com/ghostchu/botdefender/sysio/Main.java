package com.ghostchu.botdefender.sysio;

import com.ghostchu.botdefender.sysio.ipset.IPSetUtil;
import com.ghostchu.botdefender.sysio.rpc.RPCServer;
import com.ghostchu.botdefender.sysio.util.TimeUtil;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

@Log4j2
public class Main {
    private final IPSetUtil ipSetUtil = new IPSetUtil();
    private final YamlConfigurationLoader loader = YamlConfigurationLoader.builder()
            .path(Path.of("config.yml")) // Set where we will load and save to
            .build();
    private RPCServer rpcServer;
    private final short rpcPort = 4343;
    private final BlockManager blockManager = new BlockManager();

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        log.info("BotDefender Native by Ghost_chu for KarNetwork");
        log.info("This binary designed for unix like system and require ipset & iptables installed.");
        try {
            checkOS();
            checkIpset();
            checkIptables();
            ipSetUtil.setup();
        } catch (IOException | InterruptedException e) {
            log.error("Error occurred while checking requirement.", e);
        }
        log.info("Starting up RPC server...");
        try {
            this.rpcServer = new RPCServer(this, rpcPort);
            log.info("RPC server is started, Listening on {}.", rpcPort);
        } catch (IOException e) {
            log.error("Failed to start RPC server", e);
            System.exit(-1);
        }
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.nextLine();
            try {
                executeCommand(command);
            }catch (Exception e){
                log.error("Failed to execute command {}", command, e);
            }
        }
    }

    private void executeCommand(String cmd) {
        String[] commandArray = cmd.split(" ");
        String command = commandArray[0];
        String[] commandArgs = new String[commandArray.length];
        System.arraycopy(commandArray, 1, commandArgs, 0, commandArray.length - 1);
        switch (command.toLowerCase(Locale.ROOT)) {
            case "stop", "shutdown", "close", "end" -> cmdShutdown();
            case "block", "ban","jail","add","create","new" -> cmdBlock(commandArgs);
            case "unblock","unban","pardon","unjail","remove","del","delete","rm" -> cmdUnblock(commandArgs);
            default -> {
                log.warn("Unknown command: " + command);
                log.info("Available commands: stop, block, unblock");
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

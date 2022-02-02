package com.ghostchu.botdefender.sysio.rpc;

import com.ghostchu.botdefender.sysio.Main;
import com.ghostchu.botdefender.sysio.rpc.proto.BlockControllerGrpc;
import com.ghostchu.botdefender.sysio.rpc.proto.BlockControllerProto;
import com.ghostchu.botdefender.sysio.util.TimeUtil;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.SocketAddress;
import java.util.concurrent.TimeUnit;

@Log4j2
public class RPCServer {
    private final Main main;
    private int port;
    private Server server;

    public RPCServer(@NotNull Main main, int port) throws IOException {
        this.main = main;
        this.port = port;
        server = ServerBuilder.forPort(port)
                .addService(new BlockerImpl(main))
                .build();
        server.start();
        for (SocketAddress listenSocket : server.getListenSockets()) {
            log.info("Listening on " + listenSocket);
        }
    }

    public void stop() {
        try {
            server.shutdownNow().awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class BlockerImpl extends BlockControllerGrpc.BlockControllerImplBase {
        private final Main main;

        public BlockerImpl(@NotNull Main main) {
            this.main = main;
        }

        @Override
        public void blockAddress(BlockControllerProto.BlockRequest request, StreamObserver<BlockControllerProto.Address> responseObserver) {
            log.info("[RPC] Blocking {} for {}", request.getAddress(), TimeUtil.convert(request.getDuration()));
            main.blockIp(request.getAddress().getAddress(), request.getDuration());
            responseObserver.onNext(request.getAddress());
            responseObserver.onCompleted();
        }

        @Override
        public void unblockAddress(BlockControllerProto.Address request, StreamObserver<BlockControllerProto.Address> responseObserver) {
            log.info("[RPC] Unblocking {}", request.getAddress());
            main.unblockIp(request.getAddress());
            responseObserver.onNext(request);
            responseObserver.onCompleted();
        }
    }
}

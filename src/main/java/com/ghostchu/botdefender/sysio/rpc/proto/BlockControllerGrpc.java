package com.ghostchu.botdefender.sysio.rpc.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.42.1)",
    comments = "Source: blockcontroller/BlockController.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class BlockControllerGrpc {

  private BlockControllerGrpc() {}

  public static final String SERVICE_NAME = "BlockController";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.ghostchu.botdefender.sysio.rpc.proto.BlockControllerProto.BlockRequest,
      com.ghostchu.botdefender.sysio.rpc.proto.BlockControllerProto.Address> getBlockAddressMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BlockAddress",
      requestType = com.ghostchu.botdefender.sysio.rpc.proto.BlockControllerProto.BlockRequest.class,
      responseType = com.ghostchu.botdefender.sysio.rpc.proto.BlockControllerProto.Address.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ghostchu.botdefender.sysio.rpc.proto.BlockControllerProto.BlockRequest,
      com.ghostchu.botdefender.sysio.rpc.proto.BlockControllerProto.Address> getBlockAddressMethod() {
    io.grpc.MethodDescriptor<com.ghostchu.botdefender.sysio.rpc.proto.BlockControllerProto.BlockRequest, com.ghostchu.botdefender.sysio.rpc.proto.BlockControllerProto.Address> getBlockAddressMethod;
    if ((getBlockAddressMethod = BlockControllerGrpc.getBlockAddressMethod) == null) {
      synchronized (BlockControllerGrpc.class) {
        if ((getBlockAddressMethod = BlockControllerGrpc.getBlockAddressMethod) == null) {
          BlockControllerGrpc.getBlockAddressMethod = getBlockAddressMethod =
              io.grpc.MethodDescriptor.<com.ghostchu.botdefender.sysio.rpc.proto.BlockControllerProto.BlockRequest, com.ghostchu.botdefender.sysio.rpc.proto.BlockControllerProto.Address>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BlockAddress"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ghostchu.botdefender.sysio.rpc.proto.BlockControllerProto.BlockRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ghostchu.botdefender.sysio.rpc.proto.BlockControllerProto.Address.getDefaultInstance()))
              .setSchemaDescriptor(new BlockControllerMethodDescriptorSupplier("BlockAddress"))
              .build();
        }
      }
    }
    return getBlockAddressMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ghostchu.botdefender.sysio.rpc.proto.BlockControllerProto.Address,
      com.ghostchu.botdefender.sysio.rpc.proto.BlockControllerProto.Address> getUnblockAddressMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UnblockAddress",
      requestType = com.ghostchu.botdefender.sysio.rpc.proto.BlockControllerProto.Address.class,
      responseType = com.ghostchu.botdefender.sysio.rpc.proto.BlockControllerProto.Address.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ghostchu.botdefender.sysio.rpc.proto.BlockControllerProto.Address,
      com.ghostchu.botdefender.sysio.rpc.proto.BlockControllerProto.Address> getUnblockAddressMethod() {
    io.grpc.MethodDescriptor<com.ghostchu.botdefender.sysio.rpc.proto.BlockControllerProto.Address, com.ghostchu.botdefender.sysio.rpc.proto.BlockControllerProto.Address> getUnblockAddressMethod;
    if ((getUnblockAddressMethod = BlockControllerGrpc.getUnblockAddressMethod) == null) {
      synchronized (BlockControllerGrpc.class) {
        if ((getUnblockAddressMethod = BlockControllerGrpc.getUnblockAddressMethod) == null) {
          BlockControllerGrpc.getUnblockAddressMethod = getUnblockAddressMethod =
              io.grpc.MethodDescriptor.<com.ghostchu.botdefender.sysio.rpc.proto.BlockControllerProto.Address, com.ghostchu.botdefender.sysio.rpc.proto.BlockControllerProto.Address>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UnblockAddress"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ghostchu.botdefender.sysio.rpc.proto.BlockControllerProto.Address.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ghostchu.botdefender.sysio.rpc.proto.BlockControllerProto.Address.getDefaultInstance()))
              .setSchemaDescriptor(new BlockControllerMethodDescriptorSupplier("UnblockAddress"))
              .build();
        }
      }
    }
    return getUnblockAddressMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static BlockControllerStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BlockControllerStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BlockControllerStub>() {
        @java.lang.Override
        public BlockControllerStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BlockControllerStub(channel, callOptions);
        }
      };
    return BlockControllerStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static BlockControllerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BlockControllerBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BlockControllerBlockingStub>() {
        @java.lang.Override
        public BlockControllerBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BlockControllerBlockingStub(channel, callOptions);
        }
      };
    return BlockControllerBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static BlockControllerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BlockControllerFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BlockControllerFutureStub>() {
        @java.lang.Override
        public BlockControllerFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BlockControllerFutureStub(channel, callOptions);
        }
      };
    return BlockControllerFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class BlockControllerImplBase implements io.grpc.BindableService {

    /**
     */
    public void blockAddress(com.ghostchu.botdefender.sysio.rpc.proto.BlockControllerProto.BlockRequest request,
        io.grpc.stub.StreamObserver<com.ghostchu.botdefender.sysio.rpc.proto.BlockControllerProto.Address> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBlockAddressMethod(), responseObserver);
    }

    /**
     */
    public void unblockAddress(com.ghostchu.botdefender.sysio.rpc.proto.BlockControllerProto.Address request,
        io.grpc.stub.StreamObserver<com.ghostchu.botdefender.sysio.rpc.proto.BlockControllerProto.Address> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUnblockAddressMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getBlockAddressMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.ghostchu.botdefender.sysio.rpc.proto.BlockControllerProto.BlockRequest,
                com.ghostchu.botdefender.sysio.rpc.proto.BlockControllerProto.Address>(
                  this, METHODID_BLOCK_ADDRESS)))
          .addMethod(
            getUnblockAddressMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.ghostchu.botdefender.sysio.rpc.proto.BlockControllerProto.Address,
                com.ghostchu.botdefender.sysio.rpc.proto.BlockControllerProto.Address>(
                  this, METHODID_UNBLOCK_ADDRESS)))
          .build();
    }
  }

  /**
   */
  public static final class BlockControllerStub extends io.grpc.stub.AbstractAsyncStub<BlockControllerStub> {
    private BlockControllerStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BlockControllerStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BlockControllerStub(channel, callOptions);
    }

    /**
     */
    public void blockAddress(com.ghostchu.botdefender.sysio.rpc.proto.BlockControllerProto.BlockRequest request,
        io.grpc.stub.StreamObserver<com.ghostchu.botdefender.sysio.rpc.proto.BlockControllerProto.Address> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBlockAddressMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void unblockAddress(com.ghostchu.botdefender.sysio.rpc.proto.BlockControllerProto.Address request,
        io.grpc.stub.StreamObserver<com.ghostchu.botdefender.sysio.rpc.proto.BlockControllerProto.Address> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUnblockAddressMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class BlockControllerBlockingStub extends io.grpc.stub.AbstractBlockingStub<BlockControllerBlockingStub> {
    private BlockControllerBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BlockControllerBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BlockControllerBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.ghostchu.botdefender.sysio.rpc.proto.BlockControllerProto.Address blockAddress(com.ghostchu.botdefender.sysio.rpc.proto.BlockControllerProto.BlockRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBlockAddressMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.ghostchu.botdefender.sysio.rpc.proto.BlockControllerProto.Address unblockAddress(com.ghostchu.botdefender.sysio.rpc.proto.BlockControllerProto.Address request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUnblockAddressMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class BlockControllerFutureStub extends io.grpc.stub.AbstractFutureStub<BlockControllerFutureStub> {
    private BlockControllerFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BlockControllerFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BlockControllerFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ghostchu.botdefender.sysio.rpc.proto.BlockControllerProto.Address> blockAddress(
        com.ghostchu.botdefender.sysio.rpc.proto.BlockControllerProto.BlockRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBlockAddressMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ghostchu.botdefender.sysio.rpc.proto.BlockControllerProto.Address> unblockAddress(
        com.ghostchu.botdefender.sysio.rpc.proto.BlockControllerProto.Address request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUnblockAddressMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_BLOCK_ADDRESS = 0;
  private static final int METHODID_UNBLOCK_ADDRESS = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final BlockControllerImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(BlockControllerImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_BLOCK_ADDRESS:
          serviceImpl.blockAddress((com.ghostchu.botdefender.sysio.rpc.proto.BlockControllerProto.BlockRequest) request,
              (io.grpc.stub.StreamObserver<com.ghostchu.botdefender.sysio.rpc.proto.BlockControllerProto.Address>) responseObserver);
          break;
        case METHODID_UNBLOCK_ADDRESS:
          serviceImpl.unblockAddress((com.ghostchu.botdefender.sysio.rpc.proto.BlockControllerProto.Address) request,
              (io.grpc.stub.StreamObserver<com.ghostchu.botdefender.sysio.rpc.proto.BlockControllerProto.Address>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class BlockControllerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    BlockControllerBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.ghostchu.botdefender.sysio.rpc.proto.BlockControllerProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("BlockController");
    }
  }

  private static final class BlockControllerFileDescriptorSupplier
      extends BlockControllerBaseDescriptorSupplier {
    BlockControllerFileDescriptorSupplier() {}
  }

  private static final class BlockControllerMethodDescriptorSupplier
      extends BlockControllerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    BlockControllerMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (BlockControllerGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new BlockControllerFileDescriptorSupplier())
              .addMethod(getBlockAddressMethod())
              .addMethod(getUnblockAddressMethod())
              .build();
        }
      }
    }
    return result;
  }
}

package me.lenycer.grpc;

import io.grpc.ManagedChannel;
import io.grpc.netty.NettyChannelBuilder;
import io.grpc.stub.AbstractStub;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class GrpcClient<STUB extends AbstractStub<?>> {

    private final ManagedChannel channel;
    private final STUB stub;

    /**
     *
     * @param host
     * @param port
     * @param createStubFunction
     */
    public GrpcClient(String host, int port, Function<ManagedChannel, STUB> createStubFunction) {
        this.channel = NettyChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .build();
        this.stub = createStubFunction.apply(this.channel);
    }

    /**
     * shutdown
     * @throws InterruptedException
     */
    public void shutdown() throws InterruptedException {
        this.channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public STUB getStub() {
        return this.stub;
    }
}

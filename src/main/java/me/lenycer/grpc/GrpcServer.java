package me.lenycer.grpc;

import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.netty.NettyServerBuilder;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.List;

public class GrpcServer<SERVICE extends BindableService> {

    /** handler */
    private List<SERVICE> service;

    /** gRPC Server */
    private Server server;

    /**
     *
     * @param service
     */
    public GrpcServer(SERVICE ... service) {
        this.service = Arrays.asList(service);
    }
    /**
     * 서버 실행
     * @param host
     * @param port
     * @return
     * @throws IOException
     */
    public GrpcServer<SERVICE> start(String host, int port) throws IOException {
        // server 생성 & 실행
        NettyServerBuilder builder = NettyServerBuilder.forAddress(new InetSocketAddress(host, port));
        service.forEach(s -> {
            builder.addService(s);
        });
        this.server = builder.build().start();

        return this;
    }

    /**
     * 종료
     */
    public void stop() {
        if (this.server != null)
           this.server.shutdown();
    }

    /**
     * 대기
     * @throws InterruptedException
     */
    public GrpcServer<SERVICE> blockUtilShutdown() throws InterruptedException {
        if (this.server != null) {
            this.server.awaitTermination();
        }
        return this;
    }
}

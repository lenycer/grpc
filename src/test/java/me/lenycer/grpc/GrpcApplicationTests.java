package me.lenycer.grpc;

import org.junit.Test;

import me.lenycer.grpc.proto.LenycerProto.LenycerReq;
import me.lenycer.grpc.proto.LenycerProto.LenycerRes;
import me.lenycer.grpc.proto.LenycerServiceGrpc;
import me.lenycer.grpc.proto.LenycerServiceGrpc.LenycerServiceBlockingStub;

public class GrpcApplicationTests {

	/**
	 * 
	 * @throws InterruptedException 
	 */
	@Test
	public void GrpcClientTest01() throws InterruptedException {
		GrpcClient<LenycerServiceBlockingStub> client = new GrpcClient<>("localhost", 50055, LenycerServiceGrpc::newBlockingStub);
        try {
            
            LenycerReq request = LenycerReq.newBuilder()
            		.setId("1")
                    .setName("lenycer")
                    .setAddress("SungNam")
                    .build();
            LenycerRes response = client.getStub().verifyHimself(request);
            System.out.println("server say : " + response.getLenycerResult().name() + "\n" + response.getMessage());

        } finally {
            client.shutdown();
        }
	}

}

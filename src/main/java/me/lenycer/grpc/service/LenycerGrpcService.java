package me.lenycer.grpc.service;

import org.springframework.stereotype.Component;

import io.grpc.stub.StreamObserver;
import me.lenycer.grpc.proto.LenycerProto.LenycerReq;
import me.lenycer.grpc.proto.LenycerProto.LenycerRes;
import me.lenycer.grpc.proto.LenycerProto.LenycerResult;
import me.lenycer.grpc.proto.LenycerServiceGrpc.LenycerServiceImplBase;

/**
 * 
 * @author lenycer
 *
 */
@Component
public class LenycerGrpcService extends LenycerServiceImplBase{

	@Override
	public void verifyHimself(LenycerReq request, StreamObserver<LenycerRes> responseObserver) {
		
		LenycerRes.Builder builder = LenycerRes.newBuilder();
        builder.setLenycerResult(LenycerResult.RESULT_OK)
            	.setMessage("[id : "+request.getId()+"]\n"
            			+"[name : "+request.getName()+"]\n"
            			+"[address : "+request.getAddress()+"]\n"); 
        LenycerRes response = builder.build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
	}
}

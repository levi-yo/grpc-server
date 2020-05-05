package com.levi.grpc;

import com.levi.yoon.proto.*;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SampleServiceImpl extends SampleServiceGrpc.SampleServiceImplBase {

    @Override
    public void sampleCall(SampleRequest request, StreamObserver<SampleResponse> responseObserver) {
        log.info("SampleServiceImpl#sampleCall - {}, {}, CurrentThread = {}", request.getUserId(), request.getMessage(), Thread.currentThread().getName());
        SampleResponse sampleResponse = SampleResponse.newBuilder()
                .setMessage("grpc service response")
                .build();

        responseObserver.onNext(sampleResponse);
        responseObserver.onCompleted();
    }
}

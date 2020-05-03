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
        log.info("SampleServiceImpl#sampleCall - {}, {}", request.getUserId(), request.getMessage());
        ExampleRequest exampleRequest = ExampleRequest.newBuilder()
                .putRequests("a", "a")
                .putRequests("b", "b")
                .build();
        exampleRequest.getRequestsMap();
        exampleRequest.getRequestsOrDefault("a", "defaultValue");
        exampleRequest.getRequestsOrThrow("a");
        SampleResponse sampleResponse = SampleResponse.newBuilder()
                .setMessage("grpc service response")
                .build();

        responseObserver.onNext(sampleResponse);
        responseObserver.onCompleted();
    }
}

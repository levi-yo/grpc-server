package com.levi.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class GrpcRunner implements ApplicationRunner {

    private static final int PORT = 3030;
    private static final Server SERVER = ServerBuilder.forPort(PORT)
            .addService(new SampleServiceImpl())
            .build();

    @Override
    public void run(ApplicationArguments args) throws Exception {
        SERVER.start();
    }
}

package com.levi.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class GrpcRunner implements ApplicationRunner {

    private static final int PORT = 3030;
    private static final Server SERVER = ServerBuilder.forPort(PORT)
            .addService(new SampleServiceImpl())
            //.directExecutor() //nettey의 쓰레드를 이용한다. 무조건 Reactive하게 짜야함. 블록킹되면 성능최악
            .executor(Executors.newFixedThreadPool(100))
            .build();

    @Override
    public void run(ApplicationArguments args) throws Exception {
        start();
    }

    private void start() throws IOException {
        SERVER.start();
        log.info("GrpcRunner#start - listen port = {}", PORT);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.error("GrpcRunner#stop - Stopping gRPC server");
            try {
                SERVER.shutdown().awaitTermination(5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.error("GrpcRunner#stop - gRPC server was stopped");
        }));
    }
}

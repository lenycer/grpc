package me.lenycer.grpc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.grpc.BindableService;
import me.lenycer.grpc.service.LenycerGrpcService;

@SpringBootApplication
public class GrpcApplication {

	@Autowired
	LenycerGrpcService service;
	
	/**
	 * Grpc server Start with Application Runner.
	 * 
	 * @return
	 */
	@Bean
	public ApplicationRunner applicationRunner() {
		return applicationArguments -> {
			GrpcServer<BindableService> server = new GrpcServer<>(service);
			server.start("localhost", 50055).blockUtilShutdown();
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(GrpcApplication.class, args);
	}
}

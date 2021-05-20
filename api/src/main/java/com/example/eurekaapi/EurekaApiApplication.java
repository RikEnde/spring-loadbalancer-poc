package com.example.eurekaapi;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@SpringBootApplication
public class EurekaApiApplication {

	@Bean
	RouterFunction<ServerResponse> routes() {
		return route()
				.GET("/greetings", r -> ok().bodyValue(Map.of("greetings", "Hello, world!")))
				.build();
	}
	public static void main(String[] args) {
		SpringApplication.run(EurekaApiApplication.class, args);
	}

}

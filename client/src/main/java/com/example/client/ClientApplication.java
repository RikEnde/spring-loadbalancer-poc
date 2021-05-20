package com.example.client;

import reactor.core.publisher.Flux;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class ClientApplication {

	@Bean
	@LoadBalanced
	WebClient.Builder builder() {
		return WebClient.builder();
	}

	@Bean
	WebClient webClient(WebClient.Builder builder) {
		return builder.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}
}

@Component
class ConfiguredWebClientRunner {
	private static final Logger log = LoggerFactory.getLogger(ConfiguredWebClientRunner.class);

	static Flux<Greeting> call(WebClient http, String url) {
		return http.get().uri(url).retrieve().bodyToFlux(Greeting.class);
	}

	ConfiguredWebClientRunner(WebClient http) {
		call(http, "http://api/greetings").subscribe(greeting -> log.info("filter: " + greeting.toString()));
	}
}

class Greeting {
	public String getGreetings() {
		return greetings;
	}

	public void setGreetings(String greetings) {
		this.greetings = greetings;
	}

	private String greetings;

	@Override
	public String toString() {
		return "Greeting{" +
				"greetings='" + greetings + '\'' +
				'}';
	}
}

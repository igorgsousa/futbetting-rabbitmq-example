package com.futbetting.eventproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class EventProducerApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(EventProducerApplication.class, args);
	}
}

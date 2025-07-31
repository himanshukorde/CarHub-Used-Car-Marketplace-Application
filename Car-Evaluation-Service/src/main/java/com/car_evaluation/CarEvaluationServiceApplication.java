package com.car_evaluation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CarEvaluationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarEvaluationServiceApplication.class, args);
	}

}

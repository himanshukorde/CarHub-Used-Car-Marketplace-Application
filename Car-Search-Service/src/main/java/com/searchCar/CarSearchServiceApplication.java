package com.searchCar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching
@EnableFeignClients(basePackages="com.searchCar.client")
public class CarSearchServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarSearchServiceApplication.class, args);
	}

}

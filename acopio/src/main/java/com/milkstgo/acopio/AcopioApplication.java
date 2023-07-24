package com.milkstgo.acopio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class AcopioApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcopioApplication.class, args);
	}

}

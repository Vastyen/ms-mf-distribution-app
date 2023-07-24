package com.milkstgo.grasas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class GrasasApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrasasApplication.class, args);
	}

}

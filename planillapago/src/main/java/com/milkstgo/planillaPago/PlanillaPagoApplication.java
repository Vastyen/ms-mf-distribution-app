package com.milkstgo.planillaPago;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@EnableAutoConfiguration
@SpringBootApplication
public class PlanillaPagoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlanillaPagoApplication.class, args);
	}

}

package com.milkstgo.planillaPago;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class PlanillaPagoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlanillaPagoApplication.class, args);
	}

}

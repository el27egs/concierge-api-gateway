package com.ngineapps.concierge.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ConciergeApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConciergeApiGatewayApplication.class, args);
	}

}

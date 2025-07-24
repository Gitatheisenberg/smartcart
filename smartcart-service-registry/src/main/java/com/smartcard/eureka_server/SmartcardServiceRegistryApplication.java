package com.smartcard.eureka_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@EnableEurekaServer
@SpringBootApplication
public class SmartcardServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartcardServiceRegistryApplication.class, args);
	}

}

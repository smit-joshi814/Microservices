package com.smit.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServiceRegistryAddonApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServiceRegistryAddonApplication.class, args);
	}

}

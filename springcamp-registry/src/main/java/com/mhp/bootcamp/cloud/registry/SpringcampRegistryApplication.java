package com.mhp.bootcamp.cloud.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SpringcampRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcampRegistryApplication.class, args);
	}


}


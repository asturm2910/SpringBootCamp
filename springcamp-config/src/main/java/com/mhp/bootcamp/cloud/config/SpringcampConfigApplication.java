package com.mhp.bootcamp.cloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class SpringcampConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcampConfigApplication.class, args);

	}

}


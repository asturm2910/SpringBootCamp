package com.mhp.bootcamp.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(SpringSecurityConfiguration.class)

public class RestAppApplication {

	public static void main(String[] args) {

		SpringApplication.run(RestAppApplication.class, args);
	}

}


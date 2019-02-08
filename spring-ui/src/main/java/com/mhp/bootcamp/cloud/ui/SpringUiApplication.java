package com.mhp.bootcamp.cloud.ui;

import com.sun.org.apache.regexp.internal.RETest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringUiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringUiApplication.class, args);
	}

	@Bean
	//Wird benötigt wenn über das RestTemplate auf den Eureka zugegriffen wird.
	//@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}


package com.mhp.bootcamp.cloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


// Server MX Info available with: because of actuator framework and setting: management.endpoints.web.exposure.include=*

@SpringBootApplication
@EnableDiscoveryClient
public class SpringcampColorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcampColorApplication.class, args);
	}

	@RefreshScope // Enables to refresh configuration from config server with: curl -X POST http://localhost:8088/actuator/refresh
	@RestController
	class ColorRestController {

		@Value("${service.color:GREEN}")
		private String color;

		@Value("${springcamp-message:NOT AVAILABLE}")
		private String msgText;

		@GetMapping("/color")
		public String color() {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return color;
		}

		@GetMapping("/message")
		public String message() {
			return msgText;
		}
	}
}


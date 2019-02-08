package com.mhp.bootcamp.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@SpringBootApplication
public class SpringcampGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcampGatewayApplication.class, args);
	}

	@Bean
	RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
		return builder.routes().route("color",
				f -> f.path("/color")
						.filters(x -> x.hystrix(config -> config.setName("schnitzel").setFallbackUri("forward:/fallbackcolor")))
						.uri("lb://springcamp-color"))
				.build();
	}

	@RestController
	class FallbackRestController {
		@GetMapping("/fallbackcolor")
		public String fallbackcolor() {
			return "BLACK";
		}
	}


}


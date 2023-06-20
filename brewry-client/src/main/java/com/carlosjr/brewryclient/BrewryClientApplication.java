package com.carlosjr.brewryclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class BrewryClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrewryClientApplication.class, args);
	}

}

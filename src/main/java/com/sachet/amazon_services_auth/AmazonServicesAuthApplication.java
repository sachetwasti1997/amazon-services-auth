package com.sachet.amazon_services_auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class AmazonServicesAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmazonServicesAuthApplication.class, args);
	}

}

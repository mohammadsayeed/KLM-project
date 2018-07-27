package com.klm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@SpringBootApplication
@EnableOAuth2Client
public class KlmChickenFeedApplication {

	public static void main(String[] args) {
		SpringApplication.run(KlmChickenFeedApplication.class, args);
	}
}

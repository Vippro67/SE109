package com.techshopbe.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry
			.addMapping("/api/v1/**")
			.allowedOrigins("*")
			.allowedMethods("POST", "GET", "PUT", "DELETE")
			.allowCredentials(false)
			.maxAge(4800);
	}
}

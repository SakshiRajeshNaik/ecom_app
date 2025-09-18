package com.example.customer.service.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/") // allow CORS for all endpoints
				.allowedOrigins("http://localhost:3000") // your frontend
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
				.allowCredentials(true); // if you use cookies authorized
	}
}
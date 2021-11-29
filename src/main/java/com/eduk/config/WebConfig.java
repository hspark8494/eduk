package com.eduk.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Cross-Orgin 및 웹의 설정을 담당하는 클래스
 * 
 * @author 박효승
 *
 */
@Configuration
public class WebConfig implements WebMvcConfigurer{
	private final String CLIENT_URL = "http://localhost:2000"; 
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedOrigins(CLIENT_URL)
		.allowedMethods("*");
	}
}

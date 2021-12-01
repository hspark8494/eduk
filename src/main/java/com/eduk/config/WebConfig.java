package com.eduk.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.eduk.security.jwts.JwtTokenInterceptor;

import lombok.AllArgsConstructor;

/**
 * Cross-Origin 및 웹의 설정을 담당하는 클래스
 * 
 * @author 박효승
 *
 */
@Configuration
@AllArgsConstructor
public class WebConfig implements WebMvcConfigurer {
	private final String CLIENT_URL = "http://localhost:2000"; // CORS를 허용할 클라이언트 주소
	private final JwtTokenInterceptor jwtTokenInterceptor; // 토큰 필터

	/**
	 * Spring 서버의 CORS를 설정하는 메소드
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**") // 모든 URL
				.allowedOrigins(CLIENT_URL).allowedMethods("*"); // 모든 메소드 (GET, POST, PUT ...)
	}

	/**
	 * Spring 서버의 토큰 인증 인터셉터 등록
	 * PathPattern 뒤에 토큰 인증이 필요한 경로를 설정할 수 있다.
	 *
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		System.out.println("인터셉터 등록");
		//TODO : 인증 경로 설정
		registry.addInterceptor(jwtTokenInterceptor).addPathPatterns("/test");
		
	}
}

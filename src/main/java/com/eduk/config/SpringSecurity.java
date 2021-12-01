package com.eduk.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Spring Security 설정 문서
 * 
 * @author 박효승
 *
 */
@RequiredArgsConstructor
@EnableWebSecurity
public class SpringSecurity extends WebSecurityConfigurerAdapter {
	
  @Override
  protected void configure(HttpSecurity http) throws Exception {
	//CSRF, CORS 해제
    http.csrf().disable().cors().disable().headers().frameOptions().disable();
  }
  
  
  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }
}

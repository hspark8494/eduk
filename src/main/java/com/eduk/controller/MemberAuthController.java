package com.eduk.controller;

import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.eduk.domain.Member;
import com.eduk.service.MemberAuthServiceImpl;

import lombok.RequiredArgsConstructor;


/**
 * 로그인, 회원가입, 로그아웃, 비밀번호 찾기, 이메일 인증 등을 매핑하는 컨트롤러
 * 
 * @author 박효승
 *
 */
@RestController
@RequiredArgsConstructor
public class MemberAuthController {
	private final MemberAuthServiceImpl memberAuthService;

	
	//TODO : 예외처리
	@PostMapping("/login-test")
	public ResponseEntity<Map<String, Object>> login(@RequestBody Member member) throws Exception {
		Map<String, Object> responseMap = memberAuthService.login(member);

		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
	}
	
	@PostMapping("/register-test")
	public ResponseEntity<Map<String, Object>> register(@RequestBody Member member) throws Exception {
		System.out.println(member);
		Map<String, Object> responseMap = memberAuthService.register(member);
		
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
	}
	
	
	@PostMapping("/test")
	public ResponseEntity<String> test(@RequestHeader Map<String, String> headers) {
		System.out.println(headers);
		return new ResponseEntity<String>("Hello!", HttpStatus.OK);
	}
}

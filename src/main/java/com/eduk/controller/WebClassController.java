package com.eduk.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduk.domain.Channel;
import com.eduk.domain.Member;
import com.eduk.domain.WebClass;
import com.eduk.security.jwts.TokenProvider;
import com.eduk.service.WebClassService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/{channelId}")
public class WebClassController {
	
	//private final WebClassServiceImpl webClassServiceImpl;
	private final WebClassService webClassService;
	
	/**
	 * 화상수업 생성 
	 * */
	@PostMapping("/webClass")
	public WebClass createWebClass(
			@RequestBody WebClass webClass,
			@PathVariable Long channelId, 
			@RequestHeader HttpHeaders headers
			) {
		Long id = TokenProvider.getIdFormHeader(headers);
		webClass.setChannel(Channel.builder().channelId(channelId).build());
		webClass.setMember(new Member(id));
		WebClass webClassEntity = webClassService.createWebClass(webClass);
		
		return webClassEntity;
	}
	
	/**
	 * 화상수업 수정 
	 * */
	@PutMapping("/webClass/{webClassId}")
	public WebClass updateWebClass(@RequestBody WebClass webClass, @PathVariable Long webClassId) {
		WebClass webClassEntity = webClassService.updateWebClass(webClass);
		return webClassEntity;
	}
	
	/**
	 * 화상수업 삭제 
	 * */
	@DeleteMapping("/webClass/{webClassId}")
	public void deleteWebClass(@PathVariable Long webClassId) {
		webClassService.deleteWebClass(webClassId);
	}
}







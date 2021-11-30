package com.eduk.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduk.domain.Channel;
import com.eduk.domain.WebClass;
import com.eduk.service.WebClassServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/{channelId}")
public class WebClassController {
	
	private final WebClassServiceImpl webClassServiceImpl;
	
	/**
	 * 화상수업 생성 
	 * */
	@PostMapping("/webClass")
	public void createWebClass(@RequestBody WebClass webClass, @PathVariable Long channelId) {
		webClass.setChannel(Channel.builder().channelId(channelId).build());
		webClassServiceImpl.createWebClass(webClass);
	}
}

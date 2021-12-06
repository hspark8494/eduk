package com.eduk.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eduk.repository.EmailUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController

public class inviteEmailController {

	private EmailUtil emailUtil;
	
	//@PostMapping(Url_AUTH.SEND_EMAIL)
	public Map<String, Object> sendEmail(@RequestBody Map<String, Object> params){
		//log.info("email params={}", params);
		
		return emailUtil.sendEmail((String) params.get("userId"),
				(String)params.get("subject")
				,(String) params.get("body")
				);
	}
}

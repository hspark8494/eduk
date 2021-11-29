package com.eduk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	
	@RequestMapping("/myPage")
	public String myPage() {
		return "myPage/myInfo";
	}
	
	@RequestMapping("/loginForm")
	public String login() {
		return "user/login";
	}
	
	@RequestMapping("/findPassword")
	public String findPassword() {
		return "user/findPassword";
	}
	
	@RequestMapping("/resetPassword")
	public String resetPassword() {
		return "user/resetPassword";
	}
	
	@RequestMapping("/changePassword")
	public String changePassword() {
		return "user/login";
	}
}

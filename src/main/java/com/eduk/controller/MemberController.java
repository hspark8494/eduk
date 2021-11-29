package com.eduk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduk.domain.Member;
import com.eduk.service.MemberService;

@RestController
@CrossOrigin(maxAge = 3600)
public class MemberController {
	@Autowired private MemberService memberService;
	
	@PostMapping("/register")
	public String register(Member member) {
		System.out.printf("%s %s %s\n", member.getEmail(), member.getName(), member.getName());
		
		System.out.println(member);
		memberService.register(member);
		return "";
	}
	
	@GetMapping("/users")
	public String[] getUsers(String id) {
		System.out.println(id);
		return new String[] {"1","2","3"};
	}
}

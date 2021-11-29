package com.eduk.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(maxAge = 3600)
public class SampleController {

	@GetMapping("/users")
	public String[] users() {
		return new String[] {"park","kim","jang","lee"};
	}
}

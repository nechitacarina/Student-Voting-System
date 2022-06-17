package com.vote.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping("/")
	public String home(){
		return "login";
	}
	@GetMapping("/login")
	public String login(){
		return "login";
	}
}

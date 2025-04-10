package com.security.AuthSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserContoller {

	
	@GetMapping("/home")
	public String userPage() {
		
		return "user";
	}
}

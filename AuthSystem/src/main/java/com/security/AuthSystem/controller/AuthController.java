package com.security.AuthSystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.security.AuthSystem.dto.UserDto;
import com.security.AuthSystem.model.User;
import com.security.AuthSystem.service.UserService;


@Controller
public class AuthController {

	private UserService userService;
	
	public AuthController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/registration")
	public String getRegistrationPage(Model model) {
		model.addAttribute("user", new UserDto());
		return "register";
	}
	
	
	@PostMapping("/registration")
	public String saveUser(@ModelAttribute("user") UserDto userDto,Model model) {
		userService.save(userDto);
		model.addAttribute("message","Registration Successfuly");
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String login() {
		
		return "login";
	}
	
	@GetMapping("/logout")
	public String logout() {
		
		return "logout";
	}

	



}

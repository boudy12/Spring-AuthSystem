package com.security.AuthSystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import com.security.AuthSystem.Excrptions.EmailAlreadyExistsException;
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
	public String saveUser(@Valid @ModelAttribute("user") UserDto userDto,
	                     BindingResult result,
	                     RedirectAttributes redirectAttributes) {
	    
	    if (result.hasErrors()) {
	        return "register";
	    }

	    try {
	        userService.save(userDto);
	        redirectAttributes.addFlashAttribute("success", "Registration successful! Please login.");
	        return "redirect:/login";
	    } catch (EmailAlreadyExistsException e) {
	        result.rejectValue("email", "email.exists", e.getMessage());
	        return "register";
	    } catch (Exception e) {
	        result.reject("globalError", "Registration failed: " + e.getMessage());
	        return "register";
	    }
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

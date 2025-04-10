package com.security.AuthSystem.controller;

import java.util.List;

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
@RequestMapping("/admin")
public class AdminController {

	private UserService userService;
	
	public AdminController(UserService userService) {
		this.userService = userService;
	}
	
	
	@GetMapping("/dashboard")
	public String adminPage(Model model) {
		List<UserDto> users = userService.findAll();
        model.addAttribute("users", users);
		return "admin";
	}
	
	@GetMapping("/edit/{id}")
	public String editUserForm(@PathVariable int id, Model model) {
		User user = userService.findById(id);
		UserDto userDto = new UserDto();
	    userDto.setid(user.getId());
	    userDto.setFirstName(user.getFirstName());
	    userDto.setLastName(user.getLastName());
	    userDto.setEmail(user.getEmail());
	    userDto.setRole(user.getRole());
	   
	    model.addAttribute("user", userDto);
	    return "edit-user";
	}
	
	@PostMapping("/update")
	public String updateUser(@ModelAttribute("user") UserDto userDto) {

		userService.updateUser(userDto);
		return "redirect:/admin/dashboard";
	}
}

package com.security.AuthSystem.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.AuthSystem.dto.UserDto;
import com.security.AuthSystem.model.User;
import com.security.AuthSystem.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository,PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public User save(UserDto userDto) {
		 String role = userDto.getRole() != null ? userDto.getRole() : "USER";
		User user = new User(userDto.getFirstName(),userDto.getLastName(),userDto.getEmail(),passwordEncoder.encode(userDto.getPassword()) ,role);
		userRepository.save(user);
		return user;
	}

	@Override
	public List<UserDto> findAll() {
	    return userRepository.findAll().stream()
	            .map(user -> new UserDto(
	            	user.getId(),	
	                user.getFirstName(),
	                user.getLastName(),
	                user.getEmail(),
	                null,
	                user.getRole()
	            ))
	            .collect(Collectors.toList());
	}
	
		@Override
		public void updateUser(UserDto userDTO) {
		    User user = userRepository.findById(userDTO.getId())
		            .orElseThrow(() -> new RuntimeException("User not found"));

		    user.setFirstName(userDTO.getFirstName());
		    user.setLastName(userDTO.getLastName());
		    user.setEmail(userDTO.getEmail());
		    user.setRole(userDTO.getRole());

		    if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
		        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		    }
		    userRepository.save(user);
		}

	@Override
	public User findById(int id) {
	    return userRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("User not found"));
	}



}

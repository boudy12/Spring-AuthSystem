package com.security.AuthSystem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security.AuthSystem.model.User;
import com.security.AuthSystem.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	private UserRepository userRepository;
	
	@Autowired
	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		  Optional<User> optionalUser = userRepository.findByEmail(username);
		
		if(optionalUser == null) {
			throw new UsernameNotFoundException("User Not Found");
		}
		User user = optionalUser.get();
		return new CustomUserDetials(user);
	}

}

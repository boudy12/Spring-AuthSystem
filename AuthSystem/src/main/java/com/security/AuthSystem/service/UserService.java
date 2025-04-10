package com.security.AuthSystem.service;

import java.util.List;
import java.util.Optional;

import com.security.AuthSystem.dto.UserDto;
import com.security.AuthSystem.model.User;

public interface UserService {

	User save(UserDto userDto);
	List<UserDto> findAll();
	
	User findById(int id);
	
	void updateUser(UserDto userDto);
	
	
}

package com.sklearn.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.sklearn.dto.UserDTO;

public interface UserService extends UserDetailsService {
	UserDTO createUser(UserDTO userDto);
	UserDTO getUserDetailsByEmail(String email);
}

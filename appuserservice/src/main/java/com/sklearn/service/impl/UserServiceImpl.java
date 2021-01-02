package com.sklearn.service.impl;

import java.util.ArrayList;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sklearn.dto.UserDTO;
import com.sklearn.entities.UserEntity;
import com.sklearn.repositories.UserRepository;
import com.sklearn.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	UserRepository userRepository;
	BCryptPasswordEncoder bCryptPasswordEncoder; 
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public UserDTO createUser(UserDTO userDto) {
		userDto.setUsrId(UUID.randomUUID().toString());
		userDto.setEncPasswd(bCryptPasswordEncoder.encode(userDto.getPasswd()));
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
		userRepository.save(userEntity);
		
		UserDTO udt = modelMapper.map(userEntity, UserDTO.class);
		
		return udt;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity usr = userRepository.findByEmail(username);
		
		if(usr == null) throw new UsernameNotFoundException(username);
		return new User(usr.getEmail(), usr.getEncPasswd(), true, true, true, true, new ArrayList<>());  
	}

	@Override
	public UserDTO getUserDetailsByEmail(String email) {
		UserEntity usr = userRepository.findByEmail(email);
		
		if(usr == null) throw new UsernameNotFoundException(email);
		return new ModelMapper().map(usr, UserDTO.class);
	}
	
}

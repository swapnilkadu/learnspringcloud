package com.sklearn.service.impl;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sklearn.dto.UserDTO;
import com.sklearn.entities.UserEntity;
import com.sklearn.repositories.UserRepository;
import com.sklearn.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDTO createUser(UserDTO userDto) {
		userDto.setUsrId(UUID.randomUUID().toString());
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
		userEntity.setEncPasswd("test");
		userRepository.save(userEntity);
		
		return null;
	}

}

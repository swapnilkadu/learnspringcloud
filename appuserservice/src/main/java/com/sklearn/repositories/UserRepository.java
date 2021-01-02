package com.sklearn.repositories;

import org.springframework.data.repository.CrudRepository;

import com.sklearn.entities.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

	UserEntity findByEmail(String email);
}

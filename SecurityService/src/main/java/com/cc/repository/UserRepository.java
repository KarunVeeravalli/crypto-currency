package com.cc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cc.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{
	Optional<UserEntity> findByEmail(String username);
}

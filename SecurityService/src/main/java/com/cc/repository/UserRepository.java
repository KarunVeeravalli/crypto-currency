package com.cc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cc.entity.UserCredentials;

public interface UserRepository extends JpaRepository<UserCredentials, Integer>{
	Optional<UserCredentials> findByEmail(String username);
}

package com.cc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cc.entity.Users;

public interface UserRepository extends JpaRepository<Users, Integer>{

	Users findByEmail(String email);

}

package com.cc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cc.entity.Email;

public interface EmailRepository extends JpaRepository<Email, Integer>{
	
}

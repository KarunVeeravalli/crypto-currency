package com.cc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cc.entity.UnauthUser;
import com.cc.entity.UnauthUser;

public interface UnauthUserRepo extends JpaRepository<UnauthUser, Integer>{

	UnauthUser findByEmail(String mail);

}

package com.cc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cc.entity.UnAuthUser;

public interface UnAuthUserRepository extends JpaRepository<UnAuthUser, Integer>{

	UnAuthUser findByEmail(String mail);

	List<UnAuthUser> findAllByStatus(String status);

}

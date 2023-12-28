package com.cc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cc.entity.UnauthUser;
import com.cc.repository.UnauthUserRepo;

@Service
public class UnauathUserService {
	
	@Autowired
	private UnauthUserRepo repo;
	
	public UnauthUser saveUser(UnauthUser user) {
		return repo.save(user);
	}
	
	public String deleteUser(Integer id) {
		repo.deleteById(id);
		return "unauth user was added into user db";
	}
	
	public UnauthUser getUser(Integer id) {
		return repo.getById(id);
	}
	
	public UnauthUser getUserByEmail(String mail) {
		return repo.findByEmail(mail);
	}

}

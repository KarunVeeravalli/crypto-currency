package com.cc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cc.dto.RegistrationDto;
import com.cc.dto.UnAuthUser;
import com.cc.dto.Users;
import com.cc.dto.UsersDto;
import com.cc.entity.UnauthUser;
import com.cc.entity.UserCredentials;
import com.cc.feignClient.UnAuthUserExternalService;
import com.cc.feignClient.UserExternalService;
import com.cc.repository.UserRepository;

@Service
public class AuthService {
	
	
	@Autowired
	UserRepository repository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	UserExternalService userExternalService;
	
	@Autowired
	UnAuthUserExternalService unAuthUserExternalService;
	
	@Autowired
	JwtService jwtService;

    public String saveUser(UserCredentials credential) {
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        repository.save(credential);
        try {
			UserCredentials credentials = repository.findByEmail(credential.getEmail()).get();
			System.out.println("creating the  new user");
			UsersDto user = new UsersDto();
			user.setEmail(credentials.getEmail());
			user.setPassword(credentials.getPassword());
			Users users = userExternalService.addUser(user);
//			UnAuthUser users = unAuthUserExternalService.addUnAuthUser(user);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
        
        
        return "created";
    }

    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }

	public List<UserCredentials> getAllUsers() {
		return repository.findAll();
	}
}

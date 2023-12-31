package com.cc.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.cc.dto.LoginDto;
import com.cc.entity.UserCredentials;
import com.cc.feignClient.UserExternalService;
import com.cc.repository.UserRepository;


@Component
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	UserRepository repository;
	

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserCredentials> credential = repository.findByEmail(username);
        return credential.map(CustomUserDeatils::new).orElseThrow(() -> new UsernameNotFoundException("user not found with name :" + username));
    }
}

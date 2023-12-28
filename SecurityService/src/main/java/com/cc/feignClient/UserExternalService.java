package com.cc.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cc.dto.LoginDto;
import com.cc.dto.Users;
import com.cc.dto.UsersDto;

@FeignClient("USER-SERVICE")
public interface UserExternalService {
	
	@GetMapping("/user/getCredentials/{email}")
	public LoginDto getCredentials(@PathVariable String email);
	
	@PostMapping("/user/addUser")
	public Users addUser(@RequestBody UsersDto dto) ;

}

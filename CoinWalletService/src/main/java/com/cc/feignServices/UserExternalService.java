package com.cc.feignServices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cc.dto.UsersDto;

@FeignClient("USER-SERVICE")
public interface UserExternalService {
	
	@GetMapping("/user/getOnlyUser/{id}")
	public UsersDto getOnlyUser(@PathVariable Integer id);
}

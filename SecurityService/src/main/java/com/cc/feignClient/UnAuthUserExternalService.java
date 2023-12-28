package com.cc.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cc.dto.RegistrationDto;
import com.cc.dto.UnAuthUser;

@FeignClient("UNAUTHUSER-SERVICE")
public interface UnAuthUserExternalService {
	
	
	@PostMapping("/addUnAuthUser")
	public UnAuthUser addUnAuthUser(@RequestBody RegistrationDto user);
}

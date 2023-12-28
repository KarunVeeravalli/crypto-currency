package com.cc.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cc.dto.EmailDto;
import com.cc.dto.OtpDto;

@FeignClient("EMAIL-SERVICE")
public interface EmailExternalService {
	
	@GetMapping("/email/sendMail")
	public String sendMail(@RequestBody EmailDto dto);
	
	@PutMapping("/email/sendOtp")
	public String sendOtp(@RequestBody EmailDto dto);
	
	
	@PutMapping("/otp/checkOtp")
	public HttpStatus checkOtp(@RequestBody OtpDto otp);
}

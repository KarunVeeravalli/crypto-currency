package com.cc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cc.dto.EmailDto;
import com.cc.service.EmailSenderService;

@RestController
@RequestMapping("/email")
public class EmailController {
	
	@Autowired
	EmailSenderService emailSenderService;
	
	
	@GetMapping("/sendMail")
	public String sendMail(@RequestBody EmailDto dto) {
		emailSenderService.sendEmail(dto);
		return "Successfully send mail to the customer: "+dto.getToEmail();
	}
	
	
	@PutMapping("/sendOtp")
	public String sendOtp(@RequestBody EmailDto dto) {
		emailSenderService.sendOtp(dto);
		return "Successfully send mail to the customer: "+dto.getToEmail();
	}
	
}

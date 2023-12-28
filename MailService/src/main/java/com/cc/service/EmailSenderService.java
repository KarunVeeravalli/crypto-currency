package com.cc.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.cc.dto.EmailDto;
import com.cc.entity.Email;
import com.cc.entity.OtpEntity;
import com.cc.repository.EmailRepository;
import com.cc.util.RepoHelper;

@Service()
@ServletComponentScan(basePackages = {"org.springframework.mail.javamail.JavaMailSender","com.cc.util"})
public class EmailSenderService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	EmailRepository repository;
	
	@Autowired
	OtpService otpService;
	
	@Autowired
	RepoHelper helper;
	
	public void sendEmail(EmailDto dto) {
		
		SimpleMailMessage msg = new SimpleMailMessage();
		String fromMail = "kkarun701@gmail.com";
		msg.setFrom(fromMail);
		msg.setTo(dto.getToEmail());
		msg.setText(dto.getBody());
		msg.setSubject(dto.getSubject());
		Email email = new Email();
		email.setBody(dto.getBody());
		email.setFromEmail(fromMail);
		email.setSubject(dto.getSubject());
		email.setToEmail(dto.getToEmail());
		email.setTimeStamp(LocalDateTime.now());
		repository.save(email);
		javaMailSender.send(msg);
	}
	
	
	public void sendOtp(EmailDto dto) {
		SimpleMailMessage msg = new SimpleMailMessage();
		String fromMail = "kkarun701@gmail.com";
		msg.setFrom(fromMail);
		msg.setTo(dto.getToEmail());
		String otp = helper.getOtp();
		String body = helper.getOtpAndMsg(otp);
		msg.setText(body);
		msg.setSubject(dto.getSubject());
		
		
		Email email = new Email();
		email.setBody(body);
		email.setFromEmail(fromMail);
		email.setSubject(dto.getSubject());
		email.setToEmail(dto.getToEmail());
		email.setTimeStamp(LocalDateTime.now());
		repository.save(email);
		
		OtpEntity entity = new OtpEntity();
		entity.setEmail(dto.getToEmail());
		entity.setOtp(otp);
		entity.setTime(LocalDateTime.now());
		otpService.saveOtp(entity);
		
		javaMailSender.send(msg);
	}
}

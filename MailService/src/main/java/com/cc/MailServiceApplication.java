package com.cc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"org.springframework.mail.javamail.JavaMailSender","com.cc.controller","com.cc.service","com.cc.util"})
public class MailServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MailServiceApplication.class, args);
	}
}

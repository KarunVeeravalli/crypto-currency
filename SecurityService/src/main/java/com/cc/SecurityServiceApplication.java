package com.cc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = 
	{"com.cc.feignClient","com.cc.config","com.cc.controller","com.cc.repository","com.cc.service"})
@EnableDiscoveryClient
@EnableFeignClients("com.cc.feignClient")
public class SecurityServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityServiceApplication.class, args);
	}

}

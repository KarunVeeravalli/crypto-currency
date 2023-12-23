package com.cc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(scanBasePackages = {"com.cc.repository"})
//@EntityScan(basePackages = {"com.cc.entites","com.cc.controller"})
//@EnableWebMvc
@SpringBootApplication
public class CoinServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoinServiceApplication.class, args);
	}

}

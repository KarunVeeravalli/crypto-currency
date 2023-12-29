package com.cc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CoinWalletServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoinWalletServiceApplication.class, args);
	}

}


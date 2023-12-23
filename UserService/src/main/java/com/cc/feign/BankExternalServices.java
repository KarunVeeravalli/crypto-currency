package com.cc.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cc.entity.Bank;

@FeignClient("BANK-SERVICE")
public interface BankExternalServices {
	
	@GetMapping("/bank/getAllBanksByUserId/{userId}")
	public  List<Bank> getAllBanksByUserId(@PathVariable Integer userId) ;
	
	

}

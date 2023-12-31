package com.cc.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cc.dto.WalletToBankDto;
import com.cc.entity.Bank;

@FeignClient("BANK-SERVICE")
public interface BankExternalService {
	
	@PostMapping("/bank/addMoneyToBankFromWallet")
	public Bank addMoneyToBankFromWallet(@RequestBody WalletToBankDto dto);
	
	
	
	@PostMapping("/bank/sendMoneyFromBankToWallet")
	public Bank sendMoneyFromBankToWallet(@RequestBody WalletToBankDto dto);
}

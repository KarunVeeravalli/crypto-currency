package com.cc.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cc.dto.Btw;
import com.cc.entity.Wallet;

@FeignClient("WALLET-SERVICE")
public interface WalletExternalService {
	
	@GetMapping("/wallet/getWalletByUserId/{userId}")
	public Wallet getWalletByUserId(@PathVariable Integer userId);
	
	@PostMapping("/wallet/takeMoneyFromWallet/{userId}")
	public Wallet takeMoneyFromWallet(@PathVariable Integer userId, Btw sendMoneyFromWalletToBank);
	
	@PostMapping("/wallet/addMoneyToWallet/{userId}")
	public Wallet addMoneyToWallet(@PathVariable Integer userId,@RequestBody Btw addMoneyFromBankToWallet);

}

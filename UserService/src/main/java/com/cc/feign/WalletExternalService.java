package com.cc.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cc.entity.Wallet;
import com.cc.utilityHelper.GeneralResponse;

@FeignClient("WALLET-SERVICE")
public interface WalletExternalService {
	
	@GetMapping("/wallet/getWalletByUserId/{userId}")
	public Wallet getWalletByUserId(@PathVariable Integer userId);
	
	@PostMapping("/wallet/addWallet/{userId}")
	public Wallet addWallet(@PathVariable Integer userId);
	
	@DeleteMapping("/wallet/deleteWalletById/{walletId}")
	ResponseEntity<GeneralResponse> deleteWalletById(@PathVariable Integer walletId);

}

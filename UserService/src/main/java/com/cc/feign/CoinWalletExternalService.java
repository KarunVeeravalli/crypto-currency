package com.cc.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cc.entity.CoinWallet;
import com.cc.utilityHelper.GeneralResponse;

@FeignClient("COIN-WALLET-SERVICE")
public interface CoinWalletExternalService {
	
	@GetMapping("/coinWallet/getCoinWalletByUserId/{userId}")
	public CoinWallet getCoinWalletByUserId(@PathVariable Integer userId);
	
	@PostMapping("/coinWallet/addCoinWallet/{userId}")
	public CoinWallet addCoinWallet(@PathVariable Integer userId );
	
	@DeleteMapping("/coinWallet/deleteCoinWallet/{coinWalletId}")
	public ResponseEntity<GeneralResponse> deleteCoinWallet(@PathVariable Integer coinWalletId);

}

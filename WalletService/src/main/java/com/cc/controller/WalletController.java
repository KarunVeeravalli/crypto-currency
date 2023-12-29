package com.cc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cc.dto.Btw;
import com.cc.entity.Wallet;
import com.cc.exception.BankException;
import com.cc.exception.UserException;
import com.cc.exception.WalletException;
import com.cc.service.WalletService;
import com.cc.utilityHelper.GeneralResponse;


@RestController
@RequestMapping("/wallet")
public class WalletController { 
	
	@Autowired
	WalletService service;
	
	@PostMapping("/addMoneyToWallet/{userId}")
	public Wallet addMoneyToWallet(@PathVariable Integer userId,@RequestBody Btw addMoneyFromBankToWallet) throws UserException,WalletException,BankException{
		return (service.addMoneyToWallet(userId, addMoneyFromBankToWallet));
	}
	
	public Wallet bankTransactionFallback (Integer userId,Exception ex) {
		Wallet wallet = new Wallet();
		wallet.setAvailableAmount(0.0);
		wallet.setUserId(0000);
		wallet.setId(0001);
		return wallet;
	}
	
	
	
	
	@PostMapping("/addWallet/{userId}")
	public Wallet addWallet(@PathVariable Integer userId) throws UserException,WalletException,BankException{
		
		return (service.addWallet(userId));
	}
	
	@PostMapping("/takeMoneyFromWallet/{userId}")
	public Wallet takeMoneyFromWallet(@PathVariable Integer userId,@RequestBody Btw sendMoneyFromWalletToBank) throws UserException,WalletException,BankException{
		return (service.takeMoneyFromWallet(userId, sendMoneyFromWalletToBank));
	}
	
	@GetMapping("/getWalletByUserId/{userId}")
	Wallet getWalletByUserId(@PathVariable Integer userId) throws UserException,WalletException{
		
		return (service.getWalletByUserId(userId));
	}
	
	@GetMapping("/getWalletById/{walletId}")
	ResponseEntity<GeneralResponse> getWalletById(@PathVariable Integer walletId) throws UserException,WalletException{
		GeneralResponse generalResponse = new GeneralResponse();
		generalResponse.setMessage("Mwallet was found by wallet Id: "+walletId);
		generalResponse.setData(service.getWalletById(walletId));
		return ResponseEntity.ok(generalResponse);
	}
	
	@DeleteMapping("/deleteWalletById/{walletId}")
	ResponseEntity<GeneralResponse> deleteWalletById(@PathVariable Integer walletId) throws UserException,WalletException{
		GeneralResponse generalResponse = new GeneralResponse();
		generalResponse.setMessage("wallet has been deleted by wallet Id: "+walletId);
		generalResponse.setData(service.deleteWalletById(walletId));
		return ResponseEntity.ok(generalResponse);
	}
	
	
	
}

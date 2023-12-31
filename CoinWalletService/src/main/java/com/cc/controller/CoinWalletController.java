package com.cc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cc.dto.AddCoinToWalletDto;
import com.cc.dto.SellingCoinFromWalletDto;
import com.cc.entity.CoinWallet;
import com.cc.exception.CoinException;
import com.cc.exception.CoinWalletException;
import com.cc.exception.UserException;
import com.cc.exception.WalletException;
import com.cc.service.CoinWalletService;
import com.cc.utilityHelper.GeneralResponse;

@RestController
@RequestMapping("/coinWallet")
public class CoinWalletController {
	
	@Autowired
	CoinWalletService service;
	
	@PostMapping("/addCryptoCoinsToWalletByQuantity")
	public ResponseEntity<GeneralResponse> addCryptoCoinsToWalletByQuantity(@RequestBody AddCoinToWalletDto dto) throws CoinException,CoinWalletException,UserException, WalletException{
		GeneralResponse generalResponse = new GeneralResponse();
		generalResponse.setMessage("Coin added to the wallet by user Id: "+dto.getUserId()+" of coin id: "+dto.getCoinId());
		generalResponse.setData(service.addCryptoCoinsToWalletByQuantity(dto));
		return ResponseEntity.ok(generalResponse);
	}
	
//	@GetMapping("/getCoinWalletByUserId/{userId}")
//	public ResponseEntity<GeneralResponse> getCoinWalletByUserId(@PathVariable Integer userId) throws CoinWalletException,UserException{
//		GeneralResponse generalResponse = new GeneralResponse();
//		generalResponse.setMessage("CoinWallet was Found by user Id: "+userId);
//		generalResponse.setData(service.getCoinWalletByUserId(userId));
//		return ResponseEntity.ok(generalResponse);
//	}
//	
	
	@GetMapping("/getCoinWallet/{coinWalletId}")
	public ResponseEntity<GeneralResponse> getCoinWallet(@PathVariable Integer coinWalletId) throws CoinWalletException,UserException{
		GeneralResponse generalResponse = new GeneralResponse();
		generalResponse.setMessage("CoinWallet was Found by user Id: "+coinWalletId);
		generalResponse.setData(service.getCoinWallet(coinWalletId));
		return ResponseEntity.ok(generalResponse);
	}
	
	
    
	@GetMapping("/getCoinWalletByUserId/{userId}")
	public CoinWallet getCoinWalletByUserId(@PathVariable Integer userId) throws CoinWalletException,UserException{
		return service.getCoinWalletByUserId(userId);
	}
	
	@DeleteMapping("/deleteCoinWallet/{cryptoWalletId}")
	public ResponseEntity<GeneralResponse> deleteCoinWallet(@PathVariable Integer cryptoWalletId) throws CoinWalletException,UserException{
		GeneralResponse generalResponse = new GeneralResponse();
		generalResponse.setMessage("CoinWallet was deleted : "+cryptoWalletId);
		generalResponse.setData(service.deleteCoinWallet(cryptoWalletId));
		return ResponseEntity.ok(generalResponse);
	}
	
	@PutMapping("/deleteCryptoCoinsFromWalletByQuantity")
	public ResponseEntity<GeneralResponse> deleteCryptoCoinsFromWalletByQuantity(@RequestBody SellingCoinFromWalletDto dto) throws CoinException,CoinWalletException,UserException{
		GeneralResponse generalResponse = new GeneralResponse();
		generalResponse.setMessage("coin selled by user  Id: "+dto.getUserId());
		generalResponse.setData(service.deleteCryptoCoinsFromWalletByQuantity(dto));
		return ResponseEntity.ok(generalResponse);
	}
	
	@PutMapping("/deleteCryptoCoinsFromWalletByAmount")
	public ResponseEntity<GeneralResponse> deleteCryptoCoinsFromWalletByAmount(@RequestBody SellingCoinFromWalletDto dto) throws CoinException,CoinWalletException,UserException{
		GeneralResponse generalResponse = new GeneralResponse();
		generalResponse.setMessage("Coin was selled by amount: "+dto.getAmountOrQuantity());
		generalResponse.setData(service.deleteCryptoCoinsFromWalletByAmount(dto));
		return ResponseEntity.ok(generalResponse);
	}
	
	@PostMapping("/addCoinWallet/{userId}")
	public CoinWallet addCoinWallet(@PathVariable Integer userId ) throws CoinException,CoinWalletException,UserException{
		return (service.addCoinWallet(userId));
	}

	
	
}

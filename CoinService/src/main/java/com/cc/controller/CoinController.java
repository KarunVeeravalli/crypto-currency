package com.cc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cc.dto.CoinDto;
import com.cc.entites.Coins;
import com.cc.exception.CoinException;
import com.cc.service.CoinService;
import com.cc.utilityHelper.GeneralResponse;

@RestController
@RequestMapping("/coin")
public class CoinController {
	
	@Autowired
	CoinService service;
	
	@GetMapping("/getCoinById/{id}")
	public Coins getCoinById(@PathVariable Integer id) throws CoinException{
		return (service.getCoinById(id));
	}
	
	@PostMapping("/addCoin")
	public ResponseEntity<GeneralResponse> addCoin(@RequestBody CoinDto coin) throws CoinException{
		GeneralResponse generalResponse = new GeneralResponse();
		generalResponse.setMessage("Coin was added by name: "+coin.getName());
		generalResponse.setData(service.addCoin(coin));
		return ResponseEntity.ok(generalResponse);
	}
	
	@PostMapping("/addCoins")
	public ResponseEntity<GeneralResponse> addCoins(@RequestBody List<CoinDto> coins) throws CoinException{
		GeneralResponse generalResponse = new GeneralResponse();
		generalResponse.setMessage("Coins was  added into db");
		generalResponse.setData(service.addCoins(coins));
		return ResponseEntity.ok(generalResponse);
	}
	
	@DeleteMapping("/deleteCoinById")
	public ResponseEntity<GeneralResponse> deleteCoinById(Integer id) throws CoinException{
		GeneralResponse generalResponse = new GeneralResponse();
		generalResponse.setMessage("Coin was deleted by user Id: "+id);
		generalResponse.setData(service.deleteCoinById(id));
		return ResponseEntity.ok(generalResponse);
	}
	
	@GetMapping("/getAllCoins")
	public ResponseEntity<GeneralResponse> getAllCoins() throws CoinException{
		GeneralResponse generalResponse = new GeneralResponse();
		generalResponse.setMessage("Coins was Found ");
		generalResponse.setData(service.getAllCoins());
		return ResponseEntity.ok(generalResponse);
	}
	

}
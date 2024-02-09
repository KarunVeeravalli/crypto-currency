package com.cc.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.cc.utilityHelper.JwtService;
import com.cc.utilityHelper.RepoHelper;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/coin")
public class CoinController {
	
	Logger logger = LoggerFactory.getLogger(CoinController.class);
	
	@Autowired
	CoinService service;
	
	@Autowired
	JwtService jwtService;
	
	@Autowired
	RepoHelper helper;
	
	@GetMapping("/getCoinById/{id}")
	public Coins getCoinById(@PathVariable Integer id) throws CoinException{
		logger.info("getCointById method was caller in logger controller by coin Id: "+id);
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
	public ResponseEntity<GeneralResponse> getAllCoins(HttpServletRequest request) throws CoinException{
		GeneralResponse generalResponse = new GeneralResponse();
		String usermail = helper.getUsernameFromToken(request);
        System.out.println(usermail);
		generalResponse.setMessage("Coins was Found ");
		generalResponse.setData(service.getAllCoins());
		return ResponseEntity.ok(generalResponse);
	}
		
	@GetMapping("/getTopTen")
	public ResponseEntity<GeneralResponse> getTopTen(HttpServletRequest request) throws CoinException{
		GeneralResponse generalResponse = new GeneralResponse();
		String usermail = helper.getUsernameFromToken(request);
        System.out.println(usermail);
		generalResponse.setMessage("Coins was Found ");
		generalResponse.setData(service.getTopTen());
		return ResponseEntity.ok(generalResponse);
	}

}

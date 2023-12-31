package com.cc.service;

import java.util.List;

import com.cc.dto.CoinDto;
import com.cc.entites.Coins;
import com.cc.exception.CoinException;

public interface CoinService {
	
	public Coins getCoinById(Integer id) throws CoinException;
	
	public Coins addCoin(CoinDto coin) throws CoinException;
	
	public List<String> addCoins(List<CoinDto> coins) throws CoinException;
	
	public Coins deleteCoinById(Integer id) throws CoinException;
	
	public List<Coins> getAllCoins() throws CoinException;

}

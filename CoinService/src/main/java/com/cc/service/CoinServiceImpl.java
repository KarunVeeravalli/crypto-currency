package com.cc.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.core.tools.picocli.CommandLine.Help;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cc.dto.CoinDto;
import com.cc.entites.Coins;
import com.cc.exception.CoinException;
import com.cc.repository.CoinRepository;
import com.cc.utilityHelper.RepoHelper;


@Service
public class CoinServiceImpl implements CoinService {
	
	@Autowired
	CoinRepository coinRepository;
	
	@Autowired
	RepoHelper helper;
	
	@Override
	public Coins getCoinById(Integer id) throws CoinException {
		Coins coin = coinRepository.findById(id).get();
		if(coin==null) {
			throw new CoinException("Coin was not found with id: "+id);
		}
		return coin;
	}

	@Override
	public Coins addCoin(CoinDto coindto) throws CoinException {
		if(helper.checkIfCoinIsThere(coindto.getName())) {
			throw new CoinException("Coin was already there in the db : "+coindto.getName());
		}
		
		Coins coin = new Coins();
		coin.setCryptoName(coindto.getName());
		coin.setPrice(Double.parseDouble(coindto.getPrice()));
		coin.setUuid(coindto.getUuid());
		coin.setSymbol(coindto.getSymbol());
		coin.setColor(coindto.getColor());
		coin.setIconUrl(coindto.getIconUrl());
		coin.setMarketCap(coindto.getMarketCap());
		coin.setListedAt(coindto.getListedAt());
		coin.setTier(coindto.getTier());
		coin.setChange(coindto.getChange());
		coin.setRank(coindto.getRank());
		coin.setCoinRankingUrl(coindto.getCoinrankingUrl());
		coin.setBtcPrice(coindto.getBtcPrice());
		
		return coinRepository.save(coin);
	}

	@Override
	public List<String> addCoins(List<CoinDto> coins) throws CoinException {
		List<String> names = new ArrayList<>();
		for (CoinDto i : coins) {
			Coins coin = new Coins();
			coin.setCryptoName(i.getName());
			coin.setPrice(Double.parseDouble(i.getPrice()));
			coin.setUuid(i.getUuid());
			coin.setSymbol(i.getSymbol());
			coin.setColor(i.getColor());
			coin.setIconUrl(i.getIconUrl());
			coin.setMarketCap(i.getMarketCap());
			coin.setListedAt(i.getListedAt());
			coin.setTier(i.getTier());
			coin.setChange(i.getChange());
			coin.setRank(i.getRank());
			coin.setCoinRankingUrl(i.getCoinrankingUrl());
			coin.setBtcPrice(i.getBtcPrice());
			coin.setQuantity(100);
			names.add(i.getName());
			coinRepository.save(coin);
		}
		return names;
	}

	@Override
	public Coins deleteCoinById(Integer id) throws CoinException {
		Coins coin = getCoinById(id);
		if(coin==null) {
			throw new CoinException("Coin was not found with id: "+id);
		}
		
		coinRepository.deleteById(id);
		return coin;
	}

	@Override
	public List<Coins> getAllCoins() throws CoinException {
		List<Coins> coins = coinRepository.findAll();
		coins.remove(0);
		return coins;
	}

	@Override
	public List<Coins> getTopTen() throws CoinException {
		List<Coins> coins = getAllCoins();
		return coins.subList(1, 11);
	}

}

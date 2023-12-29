package com.cc.feignServices;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cc.entity.Coin;
import com.cc.entity.CoinWallet;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@FeignClient("COIN-SERVICE")
public interface CoinExternalService {
	
	@HystrixCommand(fallbackMethod = "defaultCoins")
	@GetMapping("/coin/getCoinById/{id}")
	public Coin getCoinById(@PathVariable Integer id);
	
	public static CoinWallet defaultCoins(Integer userId) {
    	CoinWallet coinWallet = new CoinWallet();
    	coinWallet.setInvestedAmount(0.0);
    	coinWallet.setTotalCryptoValue(0.0);
    	coinWallet.setUserId(userId);
    	List<Integer> coins = new ArrayList<>();
    	Coin coin = new Coin();
    	coin.setCryptoName("no coin was found due to service down");
    	coin.setBtcPrice("0.0");
    	coin.setCoinRankingUrl("noCoinUrlFound");
    	coins.add(1000);
    	coinWallet.setCoins(coins);
    	return coinWallet;
    }
}

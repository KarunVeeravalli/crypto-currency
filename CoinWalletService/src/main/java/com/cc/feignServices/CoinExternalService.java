package com.cc.feignServices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cc.entity.Coin;

@FeignClient("COIN-SERVICE")
public interface CoinExternalService {
	
	@GetMapping("/coin/getCoinById/{id}")
	public Coin getCoinById(@PathVariable Integer id);
}

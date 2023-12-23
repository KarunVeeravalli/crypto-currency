package com.cc.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.ElementCollection;
import lombok.Data;

@Data
public class CoinWallet {
	
	private Integer id;
	
	@ElementCollection
	private List<Integer> coins;
	
	@JsonIgnore
	private Integer userId;
	
	private Double totalCryptoValue;
	


}

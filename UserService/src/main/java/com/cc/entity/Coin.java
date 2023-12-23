package com.cc.entity;

import lombok.Data;

@Data
public class Coin {

	private Integer id;

	private String cryptoName;

	private Double quantity;

	private double price;
	
	private String uuid;
	
	private String symbol;
	
	private String color;
	
	private String iconUrl;
	
	private String marketCap;
	
	private Double listedAt;
	
	private Integer tier;
	
	private String change;
	
	private Integer rank;
	
	private String coinRankingUrl;
	
	private String btcPrice;


}

package com.cc.entity;

import lombok.Data;

@Data
public class Wallet {
	private Integer id;
	
	private Double availableAmount;
	
	private Integer userId;
}

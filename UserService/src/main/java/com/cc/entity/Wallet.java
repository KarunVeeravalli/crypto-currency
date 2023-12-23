package com.cc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class Wallet {

	private Integer id;
	
	private Double availableAmount;
	@JsonIgnore
	private Integer userId;

}

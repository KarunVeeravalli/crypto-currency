package com.cc.dto;

import lombok.Data;

@Data
public class AddCoinToWalletDto {
	private Integer userId;
	
	private Integer coinId;
	
	private Integer quantity;
}

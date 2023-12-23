package com.cc.dto;

import lombok.Data;

@Data
public class SellingCoinFromWalletDto {
	private Integer userId;
	private Integer coinId;
	
	private Double amountOrQuantity;
}

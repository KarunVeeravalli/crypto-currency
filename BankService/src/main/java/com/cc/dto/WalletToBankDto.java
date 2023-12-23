package com.cc.dto;

import lombok.Data;

@Data
public class WalletToBankDto {
	
	Long bankAccountNumber;
	
	Integer walletId;
	
	Double amount;

}

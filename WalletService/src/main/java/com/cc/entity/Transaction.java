package com.cc.entity;

import lombok.Data;

@Data
public class Transaction {
	
	private Integer id;
	
	private Double amount;
	
	private String transactionType;
	
	private Long fromAccount;
	
	private Integer fromWallet;
	
	private Long toAccount;
	
	private Integer toWalletId;
	
	private String status;
	
	private Integer userId;
	
	private String type;
}

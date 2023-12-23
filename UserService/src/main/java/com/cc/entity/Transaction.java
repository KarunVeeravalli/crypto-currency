package com.cc.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class Transaction {
	private Integer id;
	
	private LocalDate transactionTimeStamp;
	
	private Double amount;
	
	private String transactionType;
	
	private Double fromAccount;
	
	private Integer fromWallet;
	
	private Double toAccount;
	
	private Integer toWalletId;
	
	private String status;
	@JsonIgnore
	private Integer userId;
}

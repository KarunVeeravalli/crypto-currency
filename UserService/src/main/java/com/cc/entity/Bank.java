package com.cc.entity;

import lombok.Data;

@Data
public class Bank {
	
	private Integer id;
	
	private Long bankAccountNumber;
	
	private String bankName;
	
	private String branch;
	
	private String ifsc;
	
	private String bankHolderName;
	
	private Double amount;
	
}

package com.cc.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
@Data
public class Transaction {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private LocalDateTime transactionTimeStamp;
	
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

package com.cc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Data
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames ={"bankAccountNumber"}))
public class Bank {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private Long bankAccountNumber;
	
	private String bankName;
	
	private String branch;
	
	private String ifsc;
	
	private String bankHolderName;
	
	private Double amount;
	
//	@JsonIgnore
	private Integer userId;

}

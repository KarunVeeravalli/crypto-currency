package com.cc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Wallet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private Integer id;
	
	@Column(columnDefinition = "DOUBLE DEFAULT 0.0")
	private Double availableAmount;
	
	private Integer userId;

}

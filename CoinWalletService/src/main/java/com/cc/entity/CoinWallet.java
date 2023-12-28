package com.cc.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Data
@Entity
@Table(uniqueConstraints=
            @UniqueConstraint(columnNames={"userId"})
    )
public class CoinWallet {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	
	@ElementCollection
	private List<Integer> coins;
	
	private Integer userId;
	
	@Column(columnDefinition = "DOUBLE DEFAULT 0.0")
	private Double profit;
	
	@Column(columnDefinition = "DOUBLE DEFAULT 0.0")
	private Double investedAmount;
	
	@Column(columnDefinition = "DOUBLE DEFAULT 0.0")
	private Double totalCryptoValue;
	
	

}

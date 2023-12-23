package com.cc.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
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
	
	private Double profit;
	
	private Double investedAmount;
	
	private Double totalCryptoValue;
	
	

}
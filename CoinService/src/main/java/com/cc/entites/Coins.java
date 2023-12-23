package com.cc.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"cryptoName"}))
public class Coins {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String cryptoName;

	private Integer quantity;

	private double price;
	
	private String uuid;
	
	private String symbol;
	
	private String color;
	
	private String iconUrl;
	
	private String marketCap;
	
	private Double listedAt;
	
	private Integer tier;
	
	private String change;
	
	private Integer rank;
	
	private String coinRankingUrl;
	
	private String btcPrice;

}

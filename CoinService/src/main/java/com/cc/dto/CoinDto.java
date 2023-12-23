package com.cc.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CoinDto {
	 private String btcPrice;
     private String change;
     private String coinrankingUrl;
     private String color;
     private String iconUrl;
     private Double listedAt;
     private String lowVolume;
     private String marketCap;
     private String name;
     private String price;
     private Integer rank;
     private String symbol;
     private Integer tier;
     private String uuid;
     private List<String> sparkline;

}

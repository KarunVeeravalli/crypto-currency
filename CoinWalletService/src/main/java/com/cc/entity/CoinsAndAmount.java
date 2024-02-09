package com.cc.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class CoinsAndAmount {
	
//	private Integer coinId;
	private Integer quantity;
    private double price;

	

}

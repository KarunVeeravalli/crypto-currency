package com.cc.dto;

import java.util.List;

import lombok.Data;
@Data
public class RemoveItemFromCartDto {
	private List<Integer> items;
	private Integer userId;
	
}

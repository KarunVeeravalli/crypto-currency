package com.cc.service;

import java.util.List;

import com.cc.dto.CartDto;
import com.cc.dto.RemoveItemFromCartDto;
import com.cc.entity.Cart;
import com.cc.exception.CartException;
import com.cc.exception.UserException;

public interface CartService {
	
	public Cart getCart(Integer cartId) throws CartException, UserException;
	
	public Cart getCartByUserId(Integer userId) throws CartException,UserException;
	
	public Cart addCart(Integer userId) throws CartException,UserException;
	
	public Cart addCoinsToCart(List<CartDto>dto, Integer userId) throws CartException,UserException;
	
	public String deleteCart(Integer userId) throws CartException,UserException;
	
	public String deleteCartById(Integer cartId) throws CartException,UserException;
	
	public Cart removeElementsFromCart(RemoveItemFromCartDto dto) throws CartException,UserException;
	
}

package com.cc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cc.dto.CartDto;
import com.cc.dto.RemoveItemFromCartDto;
import com.cc.entity.Cart;
import com.cc.exception.CartException;
import com.cc.exception.UserException;
import com.cc.service.CartService;
@RestController
@RequestMapping("/coinWallet")
public class CartController {
	
	@Autowired
	CartService service;
	
	@GetMapping("/getCart/{cartId}")
	public Cart getCart(@PathVariable Integer cartId) throws CartException, UserException{
		return service.getCart(cartId);
	}
	
	@GetMapping("/getCartByUserId/{userId}")
	public Cart getCartByUserId(@PathVariable Integer userId) throws CartException,UserException{
		return service.getCartByUserId(userId);
	}
	
	@PostMapping("/addCart/{userId}")
	public Cart addCart(@PathVariable Integer userId) throws CartException,UserException{
		return service.addCart(userId);
	}
	
	@PostMapping("/addCoinsToCart/{userId}")
	public Cart addCoinsToCart(@RequestBody List<CartDto> dto, @PathVariable Integer userId) throws CartException,UserException{
		return service.addCoinsToCart(dto,userId);
	}
	
	@DeleteMapping("/deleteCart/{userId}")
	public String deleteCart(@PathVariable Integer userId) throws CartException,UserException{
		return service.deleteCart(userId);
	}
	
	@DeleteMapping("/deleteCartById/{cartId}")
	public String deleteCartById(@PathVariable Integer cartId) throws CartException,UserException{
		return service.deleteCartById(cartId);
	}
	
	@PutMapping("/removeElementsFromCart")
	public Cart removeElementsFromCart(@RequestBody RemoveItemFromCartDto dto) throws CartException,UserException{
		return service.removeElementsFromCart(dto);
	}


}

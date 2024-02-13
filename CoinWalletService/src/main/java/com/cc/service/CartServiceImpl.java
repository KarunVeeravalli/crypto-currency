package com.cc.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cc.dto.CartDto;
import com.cc.dto.RemoveItemFromCartDto;
import com.cc.entity.Cart;
import com.cc.exception.CartException;
import com.cc.exception.UserException;
import com.cc.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	CartRepository repo;

	@Override
	public Cart getCart(Integer cartId) throws CartException, UserException {
		return repo.findById(cartId).orElseThrow(()->new CartException("No Cart was found by Id: "+cartId));
	}

	@Override
	public Cart getCartByUserId(Integer userId) throws CartException, UserException {
		
		return repo.findByUserId(userId);
	}

	@Override
	public Cart addCart(Integer userId) throws CartException, UserException {
		Cart cart = new Cart();
		cart.setUserId(userId);
		return repo.save(cart);
	}

	@Override
	public Cart addCoinsToCart(List<CartDto> dto,Integer userId) throws CartException, UserException {
		Cart cart = getCartByUserId(userId);
		Map<Integer, Integer> cartItems = cart.getCoinIdAndQuantity();
		for(CartDto i: dto) {
			if (cartItems.containsKey(i.getCoinId())) {
//	            int currentValue = cartItems.get(dto.getCoinId());
	            int newValue = i.getQuantity();
	            cartItems.put(i.getCoinId(), newValue);
	        } else {
	            cartItems.put(i.getCoinId(), i.getQuantity());
	        }
		}
		
		cart.setCoinIdAndQuantity(cartItems);
		return repo.save(cart);
	}

	@Override
	public String deleteCart(Integer userId) throws CartException, UserException {
		Cart cart = getCartByUserId(userId);
		repo.deleteById(cart.getId());
		return "DELETED";
	}

	@Override
	public String deleteCartById(Integer cartId) throws CartException, UserException {
		repo.deleteById(cartId);
		return "DELETED";
	}

	@Override
	public Cart removeElementsFromCart(RemoveItemFromCartDto dto) throws CartException, UserException {
		Cart cart = getCartByUserId(dto.getUserId());
		Map<Integer, Integer> cartItems = cart.getCoinIdAndQuantity();
		for(Integer id: dto.getItems()) {
			cartItems.remove(id);
		}
		cart.setCoinIdAndQuantity(cartItems);
		return repo.save(cart);
	}

}

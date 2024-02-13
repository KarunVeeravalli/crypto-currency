package com.cc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cc.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer>{

	Cart findByUserId(Integer userId);

}

package com.cc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cc.entity.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Integer>{

	Wallet findByUserId(Integer userId);

}

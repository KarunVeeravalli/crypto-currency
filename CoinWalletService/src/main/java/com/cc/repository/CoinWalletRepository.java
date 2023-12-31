package com.cc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cc.entity.CoinWallet;

public interface CoinWalletRepository extends JpaRepository<CoinWallet, Integer>{

	CoinWallet findByUserId(Integer userId);

}

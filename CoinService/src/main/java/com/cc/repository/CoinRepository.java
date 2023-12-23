package com.cc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cc.entites.Coins;

public interface CoinRepository extends JpaRepository<Coins, Integer>{

}

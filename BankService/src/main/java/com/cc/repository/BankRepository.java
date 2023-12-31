package com.cc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cc.entity.Bank;

public interface BankRepository extends JpaRepository<Bank, Integer>{

	List<Bank> findAllByUserId(Integer userId);

	Bank findByBankAccountNumber(Long bankAccNum);

}

package com.cc.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cc.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>{
	List<Transaction> findAllByUserId(Integer userId);

	List<Transaction> findByUserIdAndTransactionTimeStamp(Integer userId, LocalDate date);

}

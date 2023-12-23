package com.cc.service;

import java.time.LocalDate;
import java.util.List;

import com.cc.Exception.TransactionalException;
import com.cc.Exception.UserException;
import com.cc.entity.Transaction;

public interface TransactionService {

	Transaction addTransaction( Transaction transactions) throws TransactionalException,UserException;
	
	Transaction getTransaction(Integer transactionId) throws TransactionalException,UserException;
	
	Transaction deleteTransaction(Integer transactionId) throws TransactionalException,UserException;
	
	Transaction updateTransaction(Integer transactionId, Transaction transactions) throws TransactionalException,UserException;
	
	List<Transaction> getAllTransactionsByUserId(Integer userId) throws TransactionalException,UserException;
	
	List<Transaction> getAllTransactionsByDateAndUserId(Integer userId,LocalDate date) throws TransactionalException,UserException;
	
//	List<Transaction> getAllTransactionByFromDateToDate(Integer userId,LocalDate fromDate,LocalDate toDate) throws TransactionalException;
	
	

}

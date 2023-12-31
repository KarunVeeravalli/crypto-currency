package com.cc.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cc.Exception.TransactionalException;
import com.cc.Exception.UserException;
import com.cc.entity.Transaction;
import com.cc.repository.TransactionRepository;
import com.cc.utilityHelper.RepoHelper;

@Service
public class TransactionServiceImpl implements TransactionService{
	
	@Autowired
	TransactionRepository repository;
	
	@Autowired
	RepoHelper repoHelper;

	@Override
	public Transaction addTransaction( Transaction transactions)
			throws TransactionalException,UserException {
		transactions.setTransactionTimeStamp(LocalDateTime.now());
		return repository.save(transactions);
		
	}

	@Override
	public Transaction getTransaction( Integer transactionId)
			throws TransactionalException,UserException {
		return repository.findById(transactionId).get();
	}

	@Override
	public Transaction deleteTransaction( Integer transactionId)
			throws TransactionalException,UserException {
		Transaction transactions = getTransaction(transactionId);
		repository.deleteById(transactionId);
		return transactions;
	}

	@Override
	public Transaction updateTransaction( Integer transactionId, Transaction transactions) throws TransactionalException,UserException {
		Transaction oldTransaction = getTransaction(transactionId);
		BeanUtils.copyProperties(transactions, oldTransaction, repoHelper.getNullPropertyNames(transactions));
		return repository.save(oldTransaction);
	}

	@Override
	public List<Transaction> getAllTransactionsByUserId(Integer userId) throws TransactionalException,UserException {
		return repository.findAllByUserId(userId);
	}

	@Override
	public List<Transaction> getAllTransactionsByDateAndUserId(Integer userId, LocalDate date)
			throws TransactionalException ,UserException{
		return repository.findByUserIdAndTransactionTimeStamp(userId, date);
	}

//	@Override
//	public List<Transaction> getAllTransactionByFromDateToDate(Integer userId, LocalDate fromDate, LocalDate toDate)
//			throws TransactionalException {
//		// TODO Auto-generated method stub
//		return null;
//	}

	
}

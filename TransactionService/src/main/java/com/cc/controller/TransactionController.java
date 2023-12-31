package com.cc.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cc.Exception.TransactionalException;
import com.cc.Exception.UserException;
import com.cc.entity.Transaction;
import com.cc.service.TransactionService;
import com.cc.utilityHelper.GeneralResponse;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
	
	@Autowired
	TransactionService transactionService;
	
	@PostMapping("/addTransaction")
	private ResponseEntity<GeneralResponse> addTransaction(@RequestBody Transaction transactions) throws TransactionalException, UserException{
		GeneralResponse generalResponse = new GeneralResponse();
		generalResponse.setMessage("Transaction was added by user Id: "+transactions.getUserId());
		generalResponse.setData(transactionService.addTransaction(transactions));
		return ResponseEntity.ok(generalResponse);
	}
	
	@GetMapping("/getTransaction/{transactionId}")
	private ResponseEntity<GeneralResponse> getTransaction(@PathVariable Integer transactionId) throws TransactionalException, UserException{
		GeneralResponse generalResponse = new GeneralResponse();
		generalResponse.setMessage("Transaction was found by transaction Id: "+transactionId);
		generalResponse.setData(transactionService.getTransaction(transactionId));
		return ResponseEntity.ok(generalResponse);
	}
	
	@DeleteMapping("/deleteTransaction/{transactionId}")
	private ResponseEntity<GeneralResponse> deleteTransaction(@PathVariable Integer transactionId) throws TransactionalException, UserException{
		GeneralResponse generalResponse = new GeneralResponse();
		generalResponse.setMessage("Transaction was selled by transaction Id: "+transactionId);
		generalResponse.setData(transactionService.deleteTransaction(transactionId));
		return ResponseEntity.ok(generalResponse);
	}
	
	@PutMapping("/updateTransaction/{transactionId}")
	private ResponseEntity<GeneralResponse> updateTransaction(@PathVariable Integer transactionId,@RequestBody Transaction transactions) throws TransactionalException, UserException{
		GeneralResponse generalResponse = new GeneralResponse();
		generalResponse.setMessage("Transaction was update by transaction Id: "+transactionId);
		generalResponse.setData(transactionService.updateTransaction(transactionId, transactions));
		return ResponseEntity.ok(generalResponse);
	}
	
	@GetMapping("/getAllTransactionsByUserId/{userId}")
	private ResponseEntity<GeneralResponse> getAllTransactionsByUserId(@PathVariable Integer userId) throws TransactionalException, UserException{
		GeneralResponse generalResponse = new GeneralResponse();
		generalResponse.setMessage("All Transaction was found by user Id: "+userId);
		generalResponse.setData(transactionService.getAllTransactionsByUserId(userId));
		return ResponseEntity.ok(generalResponse);
	}
	
	@GetMapping("/getAllTransactionsByDateAndUserId/{userId}/{date}")
	private ResponseEntity<GeneralResponse> getAllTransactionsByDateAndUserId(@PathVariable Integer userId,@PathVariable LocalDate date) throws TransactionalException, UserException{
		GeneralResponse generalResponse = new GeneralResponse();
		generalResponse.setMessage("All Transaction was found by user Id: "+userId+" by date: "+date);
		generalResponse.setData(transactionService.getAllTransactionsByDateAndUserId(userId, date));
		return ResponseEntity.ok(generalResponse);
	}
	
//	@GetMapping("/getAllTransactionByFromDateToDate/{userId}/{fromDate}/{toDate}")
//	private ResponseEntity<GeneralResponse> getAllTransactionByFromDateToDate(@PathVariable Integer userId,@PathVariable LocalDate fromDate,@PathVariable LocalDate toDate,Principal user) throws UsersException,TransactionalException{
//		GeneralResponse generalResponse = new GeneralResponse();
//		generalResponse.setMessage("All Transaction was found by user Id: "+userId+"from date: "+fromDate+" to date: "+toDate);
//		generalResponse.setData(transactionService.getAllTransactionByFromDateToDate(userId, fromDate, toDate, user));
//		return ResponseEntity.ok(generalResponse);
//	}
//	
	
	
}

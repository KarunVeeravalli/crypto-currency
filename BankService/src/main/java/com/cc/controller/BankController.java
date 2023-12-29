package com.cc.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.cc.Exception.BankException;
import com.cc.dto.WalletToBankDto;
import com.cc.entity.Bank;
import com.cc.service.BankService;
import com.cc.utilityHelper.GeneralResponse;

@RestController
@RequestMapping("/bank")
public class BankController {
	
	Logger logger = LoggerFactory.getLogger(BankController.class);
	
	@Autowired
	BankService bankService;
	
	@PostMapping("/addBank")
	private ResponseEntity<GeneralResponse>  addBank(@RequestBody Bank bank) throws BankException{
		GeneralResponse generalResponse = new GeneralResponse();
		generalResponse.setMessage("Bank was added by user Id: "+bank.getUserId());
		generalResponse.setData(bankService.addBank(bank));
		logger.info("request comes to bank-service and hit the url: /bank/addBank");
		logger.info("Bank was added by the user id: "+bank.getUserId()+" with bank name "+bank.getBankName());
		return ResponseEntity.ok(generalResponse);
	}
	
	
	@PutMapping("/updateBank/{bankId}")
	private ResponseEntity<GeneralResponse> updateBank(@PathVariable Integer bankId,@RequestBody Bank bank) throws BankException{
		GeneralResponse generalResponse = new GeneralResponse();
		generalResponse.setMessage("Bank was updated by user Id: "+bank.getUserId());
		generalResponse.setData(bankService.updateBank(bankId, bank));
		logger.info("request comes to bank-service and hit the url: /bank/updateBank/{bankId}");
		logger.info("bank was updated by the bank Id: "+bankId);
		return ResponseEntity.ok(generalResponse);
	}
	
	@DeleteMapping("/deleteBank/{bankId}")
	private ResponseEntity<GeneralResponse>  deleteBank (@PathVariable Integer bankId) throws BankException{
		GeneralResponse generalResponse = new GeneralResponse();
		generalResponse.setMessage("Bank was deleted ");
		generalResponse.setData(bankService.deleteBank(bankId));
		logger.info("request comes to bank-service and hit the url: /bank/deleteBank/{bankId}");
		logger.info("bank was deleted by the user with bank id: "+bankId);
		return ResponseEntity.ok(generalResponse);
	}
	
	@GetMapping("/getBankById/{bankId}")
	private ResponseEntity<GeneralResponse> getBankById(@PathVariable Integer bankId) throws BankException{
		GeneralResponse generalResponse = new GeneralResponse();
		generalResponse.setMessage("Bank was Found by bank Id: ");
		generalResponse.setData(bankService.getBankById( bankId));
		logger.info("request comes to bank-service and hit the url: /bank/getBankById/{bankId}");
		logger.info("bank was found with bank id: "+bankId);
		return ResponseEntity.ok(generalResponse);
	}
	
	
//	@GetMapping("/getAllBanksByUserId/{userId}")
//	private ResponseEntity<GeneralResponse> getAllBanksByUserId(@PathVariable Integer userId) throws BankException{
//		GeneralResponse generalResponse = new GeneralResponse();
//		generalResponse.setMessage("Found All Banks by user Id: "+userId);
//		generalResponse.setData(bankService.getAllBanksByUserId(userId));
//		return ResponseEntity.ok(generalResponse);
//	}
	
	@GetMapping("/getAllBanksByUserId/{userId}")
	public List<Bank> getAllBanksByUserId(@PathVariable Integer userId) throws BankException{
		logger.info("request comes to bank-service and hit the url: /bank/getAllBanksByUserId/{bankId}");
		logger.info("all banks found with user id: "+userId);
		return bankService.getAllBanksByUserId(userId);
	}
	
	@GetMapping("/getBankByAccountNumber/{bankAccNum}")
	public Bank getBankByAccountNumber(@PathVariable Long bankAccNum) throws BankException{
		logger.info("request comes to bank-service and hit the url: /bank/getBankById/{bankId}");
		logger.info("bank account was found with bank id: "+bankAccNum);
		return bankService.getBankByAccountNumber(bankAccNum);
	}
	
	@PostMapping("/addMoneyToBankFromWallet")
	public Bank addMoneyToBankFromWallet(@RequestBody WalletToBankDto dto) throws BankException{
		logger.info("request comes to bank-service and hit the url: /bank/addMoneyToBankFromWallet");
		logger.info("money was added into bank from wallet: "+dto.getWalletId() +"  and bank : "+dto.getBankAccountNumber()+ " of amount "+dto.getAmount());
		return bankService.addMoneyToBankFromWallet(dto);
	}
	
	@PostMapping("/sendMoneyFromBankToWallet")
	public Bank sendMoneyFromBankToWallet(@RequestBody WalletToBankDto dto) throws BankException{
		logger.info("request comes to bank-service and hit the url: /bank/sendMoneyFromBankToWallet");
		logger.info("money was send to the wallet : "+dto.getWalletId()+" from bank : "+dto.getBankAccountNumber());
		return bankService.sendMoneyFromBankToWallet(dto);
	}
}

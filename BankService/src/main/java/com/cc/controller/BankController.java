package com.cc.controller;

import java.security.Principal;
import java.util.List;

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

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;

@RestController
@RequestMapping("/bank")
public class BankController {

	@Autowired
	BankService bankService;
	
	@PostMapping("/addBank")
	private ResponseEntity<GeneralResponse>  addBank(@RequestBody Bank bank) throws BankException{
		GeneralResponse generalResponse = new GeneralResponse();
		generalResponse.setMessage("Bank was added by user Id: "+bank.getUserId());
		generalResponse.setData(bankService.addBank( bank));
		return ResponseEntity.ok(generalResponse);
	}
	
	
	@PutMapping("/updateBank/{bankId}")
	private ResponseEntity<GeneralResponse> updateBank(@PathVariable Integer bankId,@RequestBody Bank bank) throws BankException{
		GeneralResponse generalResponse = new GeneralResponse();
		generalResponse.setMessage("Bank was updated by user Id: "+bank.getUserId());
		generalResponse.setData(bankService.updateBank(bankId, bank));
		return ResponseEntity.ok(generalResponse);
	}
	
	@DeleteMapping("/deleteBank/{bankId}")
	private ResponseEntity<GeneralResponse>  deleteBank (@PathVariable Integer bankId) throws BankException{
		GeneralResponse generalResponse = new GeneralResponse();
		generalResponse.setMessage("Bank was deleted ");
		generalResponse.setData(bankService.deleteBank(bankId));
		return ResponseEntity.ok(generalResponse);
	}
	
	@GetMapping("/getBankById/{bankId}")
	private ResponseEntity<GeneralResponse> getBankById(@PathVariable Integer bankId) throws BankException{
		GeneralResponse generalResponse = new GeneralResponse();
		generalResponse.setMessage("Bank was Found by bank Id: ");
		generalResponse.setData(bankService.getBankById( bankId));
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
		return bankService.getAllBanksByUserId(userId);
	}
	
	@GetMapping("/getBankByAccountNumber/{bankAccNum}")
	public Bank getBankByAccountNumber(@PathVariable Long bankAccNum) throws BankException{
		return bankService.getBankByAccountNumber(bankAccNum);
	}
	
	@PostMapping("/addMoneyToBankFromWallet")
	public Bank addMoneyToBankFromWallet(@RequestBody WalletToBankDto dto) throws BankException{
		return bankService.addMoneyToBankFromWallet(dto);
	}
	
	@PostMapping("/sendMoneyFromBankToWallet")
	public Bank sendMoneyFromBankToWallet(@RequestBody WalletToBankDto dto) throws BankException{
		return bankService.sendMoneyFromBankToWallet(dto);
	}
}
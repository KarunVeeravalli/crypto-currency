package com.cc.service;

import java.util.List;

import com.cc.Exception.BankException;
import com.cc.dto.WalletToBankDto;
import com.cc.entity.Bank;

public interface BankService {
	
	public Bank addBank(Bank bank) throws BankException;
	
	public Bank updateBank(Integer bankId, Bank bank) throws BankException;
	
	public Bank deleteBank(Integer bankId) throws BankException;
	
	public Bank getBankById(Integer bankId) throws BankException;
	
	public Bank getBankByAccountNumber(Long bankAccNum) throws BankException;
	
	public List<Bank> getAllBanksByUserId(Integer userId) throws BankException;
	
	public Bank addMoneyToBankFromWallet(WalletToBankDto dto) throws BankException;
	
	public Bank sendMoneyFromBankToWallet(WalletToBankDto dto) throws BankException;
	


}

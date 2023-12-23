package com.cc.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cc.Exception.BankException;
import com.cc.dto.WalletToBankDto;
import com.cc.entity.Bank;
import com.cc.entity.Transaction;
import com.cc.feignClient.TransactionExternalService;
import com.cc.repository.BankRepository;
import com.cc.utilityHelper.RepoHelper;

@Service
public class BankServiceImpl implements BankService {
	
	@Autowired
	BankRepository repository;
	
	@Autowired
	RepoHelper helper;
	
	@Autowired
	TransactionExternalService transactionExternalService;

	@Override
	public Bank addBank(Bank bank) throws BankException {
		return repository.save(bank);
	}

	@Override
	public Bank updateBank(Integer bankId, Bank bank) throws BankException {
		Bank oldBank = getBankById(bankId);
		BeanUtils.copyProperties(bank, oldBank,helper.getNullPropertyNames(bank));
		return repository.save(oldBank);
	}

	@Override
	public Bank deleteBank(Integer bankId) throws BankException {
		Bank bank = getBankById(bankId);
		repository.deleteById(bankId);
		return bank;
	}

	@Override
	public Bank getBankById(Integer bankId) throws BankException {
		return repository.findById(bankId).orElseThrow(()->new BankException("Bank not Found with Id: "+bankId));
	}

	@Override
	public List<Bank> getAllBanksByUserId(Integer userId) throws BankException {
		return repository.findAllByUserId(userId);
	}
	
	@Override
	public Bank getBankByAccountNumber(Long bankAccNum) throws BankException {
		return repository.findByBankAccountNumber(bankAccNum);
	}

	@Override
	public Bank addMoneyToBankFromWallet(WalletToBankDto dto) throws BankException {
		Bank bank = getBankByAccountNumber(dto.getBankAccountNumber());
		Transaction transaction = new Transaction();
		transaction.setFromWallet(dto.getWalletId());
		transaction.setToAccount(dto.getBankAccountNumber());
		transaction.setAmount(dto.getAmount());
		transaction.setStatus("SUCCESS");
		transaction.setTransactionType("WALLET-BANK");
		transaction.setType("CREDIT");
		transaction.setUserId(bank.getUserId());
		transactionExternalService.addTransaction(transaction);
		bank.setAmount(bank.getAmount()+dto.getAmount());
		return repository.save(bank);
	}
	

	@Override
	public Bank sendMoneyFromBankToWallet(WalletToBankDto dto) throws BankException {
		System.out.println(dto.getBankAccountNumber());
		Bank bank = getBankByAccountNumber(dto.getBankAccountNumber());
		Transaction transaction = new Transaction();
		transaction.setFromWallet(dto.getWalletId());
		transaction.setToAccount(dto.getBankAccountNumber());
		transaction.setAmount(dto.getAmount());
		transaction.setStatus("SUCCESS");
		transaction.setTransactionType("BANK-WALLET");
		transaction.setType("DEBIT");
		transaction.setUserId(bank.getUserId());
		transactionExternalService.addTransaction(transaction);
		bank.setAmount(bank.getAmount()-dto.getAmount());
		return repository.save(bank);
	}

	

	

}

package com.cc.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cc.Exception.BankException;
import com.cc.dto.Btw;
import com.cc.dto.WalletToBankDto;
import com.cc.entity.Bank;
import com.cc.entity.Transaction;
import com.cc.feignClient.TransactionExternalService;
import com.cc.feignClient.WalletExternalService;
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
	
	@Autowired
	WalletExternalService walletExternalService;
	
	Logger logger = LoggerFactory.getLogger(BankServiceImpl.class);

	@Override
	public Bank addBank(Bank bank) throws BankException {
		if(helper.checkIfBankIsThere(bank.getBankAccountNumber())) {
			throw new BankException("Bank Was Already there in the db with account Number: "+bank.getBankAccountNumber());
		}
		
		return repository.save(bank);
	}

	@Override
	public Bank updateBank(Integer bankId, Bank bank) throws BankException {
		Bank oldBank = getBankById(bankId);
		if(oldBank==null) {
			throw new BankException("No bank Was found with bank Id: "+bankId);
		}
		BeanUtils.copyProperties(bank, oldBank,helper.getNullPropertyNames(bank));
		return repository.save(oldBank);
	}

	@Override
	public Bank deleteBank(Integer bankId) throws BankException {
		Bank bank = getBankById(bankId);
		if(bank==null) {
			throw new BankException("No bank Was found with bank Id: "+bankId);
		}
		repository.deleteById(bankId);
		return bank;
	}

	@Override
	public Bank getBankById(Integer bankId) throws BankException {
//		if(helper.checkIfBankIsThereByBankId(bankId)) {
//			throw new BankException("No bank Was found with bank Id: "+bankId);
//		}
		return repository.findById(bankId).orElseThrow(()->new BankException("Bank not Found with Id: "+bankId));
	}

	@Override
	public List<Bank> getAllBanksByUserId(Integer userId) throws BankException {
		List<Bank> banks = repository.findAllByUserId(userId);
		if(banks.size()==0) {
			throw new BankException("No banks was found with userId: "+userId);
		}
		return repository.findAllByUserId(userId);
	}
	
	@Override
	public Bank getBankByAccountNumber(Long bankAccNum) throws BankException {
//		if(!helper.checkIfBankIsThere(bankAccNum)) {
//			throw new BankException("bank was not found with bank Account number : "+bankAccNum);
//		} 
		return repository.findByBankAccountNumber(bankAccNum);
	}

	@Override
	public Bank addMoneyToBankFromWallet(WalletToBankDto dto) throws BankException {
//		if(!helper.checkIfBankIsThere(dto.getBankAccountNumber())) {
//			throw new BankException("bank was not found with bank Account number : "+dto.getBankAccountNumber());
//		}
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
//		if(!helper.checkIfBankIsThere(dto.getBankAccountNumber())) {
//			throw new BankException("bank was not found with bank Account number : "+dto.getBankAccountNumber());
//		}
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
		Btw btw = new Btw();
		btw.setAccountNumber(bank.getBankAccountNumber());
		btw.setAmount(dto.getAmount());
		btw.setWalletId(dto.getWalletId());
//		walletExternalService.addMoneyToWallet(bank.getUserId(),btw );
		bank.setAmount(bank.getAmount()-dto.getAmount());
		return repository.save(bank);
	}

	

	

}

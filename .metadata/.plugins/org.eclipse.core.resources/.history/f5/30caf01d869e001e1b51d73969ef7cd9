package com.cc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cc.dto.Btw;
import com.cc.dto.WalletToBankDto;
import com.cc.entity.Transaction;
import com.cc.entity.Wallet;
import com.cc.exception.BankException;
import com.cc.exception.UserException;
import com.cc.exception.WalletException;
import com.cc.feignClient.BankExternalService;
import com.cc.feignClient.TransactionExternalService;
import com.cc.repository.WalletRepository;
@Service
public class WalletServiceImpl implements WalletService{
	
	@Autowired
	WalletRepository repository;
	
	@Autowired
	BankExternalService bankExternalService;
	
	@Autowired
	TransactionExternalService transactionExternalService;
	
	

	@Override
	public Wallet addMoneyToWallet(Integer userId, Btw addMoneyFromBankToWallet)
			throws UserException, WalletException, BankException {
		Wallet wallet = getWalletByUserId(userId);
		Transaction transaction = new Transaction();
		transaction.setFromWallet(wallet.getId());
		transaction.setToAccount(addMoneyFromBankToWallet.getAccountNumber());
		transaction.setAmount(addMoneyFromBankToWallet.getAmount());
		transaction.setTransactionType("BANK-WALLET");
		transaction.setUserId(userId);
		WalletToBankDto dto = new WalletToBankDto();
		dto.setAmount(addMoneyFromBankToWallet.getAmount());
		dto.setBankAccountNumber(addMoneyFromBankToWallet.getAccountNumber());
		dto.setWalletId(wallet.getId());
		bankExternalService.sendMoneyFromBankToWallet(dto);
		transaction.setStatus("SUCCESS");
		transaction.setType("CREDIT");
		transactionExternalService.addTransaction(transaction);
		wallet.setAvailableAmount(wallet.getAvailableAmount()+addMoneyFromBankToWallet.getAmount());
		return repository.save(wallet);
	}

	@Override
	public Wallet takeMoneyFromWallet(Integer userId, Btw sendMoneyFromWalletToBank)
			throws UserException, WalletException, BankException {
		Wallet wallet = getWalletByUserId(userId);
		Transaction transaction = new Transaction();
		transaction.setFromWallet(wallet.getId());
		transaction.setToAccount(sendMoneyFromWalletToBank.getAccountNumber());
		transaction.setAmount(sendMoneyFromWalletToBank.getAmount());
		transaction.setTransactionType("WALLET-BANK");
		transaction.setUserId(userId);
		//here we have to create transaction
		if(sendMoneyFromWalletToBank.getAmount()<=wallet.getAvailableAmount()) {
			wallet.setAvailableAmount(wallet.getAvailableAmount()-sendMoneyFromWalletToBank.getAmount());
			
			transaction.setStatus("SUCCESS");
			transaction.setType("DEBIT");
			WalletToBankDto dto = new WalletToBankDto();
			dto.setAmount(sendMoneyFromWalletToBank.getAmount());
			dto.setBankAccountNumber(sendMoneyFromWalletToBank.getAccountNumber());
			dto.setWalletId(wallet.getId());
			bankExternalService.addMoneyToBankFromWallet(dto);
		}
		else {
			transaction.setStatus("FAILURE");
			throw new WalletException("not suffient funds in your wallet");
		}
		transactionExternalService.addTransaction(transaction);
		return repository.save(wallet);
	}

	@Override
	public Wallet getWalletByUserId(Integer userId) throws UserException, WalletException {
		return repository.findByUserId(userId);
	}

	@Override
	public Wallet getWalletById( Integer walletId) throws UserException, WalletException {
		return repository.findById(walletId).orElseThrow(()->new WalletException("not found wallet by wallet id: "+walletId));
	}

	@Override
	public Wallet deleteWalletById(Integer walletId) throws UserException, WalletException {
		Wallet wallet = getWalletById(walletId);
		repository.deleteById(walletId);
		return wallet;
	}

	@Override
	public Wallet addWallet(Integer userId) throws UserException, WalletException {
		Wallet wallet = new Wallet();
		wallet.setUserId(userId);
		return repository.save(wallet);
	}

}

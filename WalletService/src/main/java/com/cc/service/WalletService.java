package com.cc.service;

import com.cc.dto.Btw;
import com.cc.entity.Wallet;
import com.cc.exception.BankException;
import com.cc.exception.UserException;
import com.cc.exception.WalletException;

public interface WalletService {
	
	Wallet addMoneyToWallet(Integer userId,Btw addMoneyFromBankToWallet) throws UserException,WalletException,BankException;
	
	Wallet takeMoneyFromWallet(Integer userId, Btw sendMoneyFromWalletToBank) throws UserException,WalletException,BankException;
	
	Wallet getWalletByUserId(Integer userId) throws UserException,WalletException;
	
	Wallet getWalletById(Integer walletId) throws UserException,WalletException;
	
	Wallet deleteWalletById(Integer walletId) throws UserException,WalletException;
	
	Wallet addWallet(Integer userId) throws UserException,WalletException;
}

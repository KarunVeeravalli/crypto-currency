package com.cc.service;

import java.util.List;

import com.cc.dto.AddCoinToWalletDto;
import com.cc.dto.CoinDto;
import com.cc.dto.SellingCoinFromWalletDto;
import com.cc.entity.Coin;
import com.cc.entity.CoinWallet;
import com.cc.exception.CoinException;
import com.cc.exception.CoinWalletException;
import com.cc.exception.UserException;
import com.cc.exception.WalletException;

public interface CoinWalletService {
	
	CoinWallet addCryptoCoinsToWalletByQuantity(AddCoinToWalletDto dto) throws CoinException,CoinWalletException,UserException, WalletException;
	
	CoinWallet getCoinWalletByUserId(Integer userId) throws CoinWalletException,UserException;
	
	CoinWallet getCoinWallet(Integer coinWalletId) throws CoinWalletException,UserException;
	
	CoinWallet deleteCoinWallet(Integer coinWalletId) throws CoinWalletException,UserException;
	
	CoinWallet deleteCryptoCoinsFromWalletByQuantity( SellingCoinFromWalletDto dto) throws CoinException,CoinWalletException,UserException;
	
	CoinWallet deleteCryptoCoinsFromWalletByAmount(SellingCoinFromWalletDto dto) throws CoinException,CoinWalletException,UserException;
	
	CoinWallet addCoinWallet(Integer userId) throws CoinWalletException,UserException;
	
	List<Coin> getAllCoinsByUserId(Integer userId) throws CoinWalletException,UserException,CoinException;
	
	List<CoinDto> getAllCoinsFromWalletByUserId(Integer userId) throws CoinWalletException,UserException,CoinException;
}

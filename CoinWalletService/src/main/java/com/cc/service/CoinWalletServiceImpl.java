package com.cc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cc.dto.AddCoinToWalletDto;
import com.cc.dto.Btw;
import com.cc.dto.SellingCoinFromWalletDto;
import com.cc.entity.Coin;
import com.cc.entity.CoinWallet;
import com.cc.entity.Wallet;
import com.cc.exception.CoinException;
import com.cc.exception.CoinWalletException;
import com.cc.exception.UserException;
import com.cc.exception.WalletException;
import com.cc.feignServices.CoinExternalService;
import com.cc.feignServices.UserExternalService;
import com.cc.feignServices.WalletExternalService;
import com.cc.repository.CoinWalletRepository;

@Service
public class CoinWalletServiceImpl implements CoinWalletService{
	@Autowired
	CoinWalletRepository repository;
	
	@Autowired
	WalletExternalService walletExternalService;
	
	@Autowired
	CoinExternalService coinExternalService;
	
	@Autowired
	UserExternalService userExternalService;

	@Override
	public CoinWallet addCryptoCoinsToWalletByQuantity(AddCoinToWalletDto dto)
			throws CoinException, CoinWalletException, UserException, WalletException {
		CoinWallet coinWallet = getCoinWalletByUserId(dto.getUserId());
		if(coinWallet==null) {
			throw new CoinWalletException("Coin Wallet was not found with user id: "+dto.getUserId());
		}
		
		List<Integer> walletCoins =  coinWallet.getCoins();
		Coin coin = coinExternalService.getCoinById(dto.getCoinId());
		if(coin==null) {
			throw new CoinException("Coin was not found with id: "+dto.getCoinId());
		}
		
		if(coin.getQuantity()>=dto.getQuantity()) {
			Wallet wallet = walletExternalService.getWalletByUserId(dto.getUserId());
			if(wallet.getAvailableAmount()>((coin.getPrice())*dto.getQuantity())) {
				for(int i=0;i<dto.getQuantity();i++) {
					coinWallet.getCoins().add(coin.getId());
					Double coinWalletAmount = coinWallet.getTotalCryptoValue()+coin.getPrice();
					Double coinWalletInvestedAmount = coinWallet.getInvestedAmount()+coin.getPrice();
					coinWallet.setInvestedAmount(coinWalletInvestedAmount);
					coinWallet.setTotalCryptoValue(coinWalletAmount);
					coinWallet.setProfit(coinWalletAmount-coinWalletInvestedAmount);
				}
				Btw btw = new Btw();
				btw.setAccountNumber(Long.valueOf("101010101"));
				btw.setAmount(Double.valueOf((coin.getPrice())*dto.getQuantity()));
				btw.setWalletId(wallet.getId());
				walletExternalService.takeMoneyFromWallet(dto.getUserId(),btw );
			}
			else {
				throw new WalletException("Amount is sufficient to by coin "+coin.getCryptoName()+" with quantity "+dto.getQuantity());
			}
		}
		else {
			throw new CoinException("Sorry! there are not enough coin left, you can buy under "+coin.getQuantity()+" coins.");
		}
		return repository.save(coinWallet);
	}

	@Override
	public CoinWallet getCoinWalletByUserId(Integer userId) throws CoinWalletException, UserException {
		CoinWallet coinWallet = repository.findByUserId(userId);
		if(coinWallet==null) {
			throw new CoinWalletException("Coin Wallet was not found with user id: "+userId);
		}
		
		Double amount = 0.0;
		for(int i: coinWallet.getCoins()) {
			Double coinPrice =  coinExternalService.getCoinById(i).getPrice();
			amount+=coinPrice;
		}
		coinWallet.setTotalCryptoValue(amount);
		Double profit = amount - coinWallet.getInvestedAmount();
		coinWallet.setProfit(profit);
		
		return repository.save(coinWallet);
	}

	@Override
	public CoinWallet getCoinWallet(Integer coinWalletId) throws CoinWalletException, UserException {
		return repository.findById(coinWalletId).orElseThrow(()->new CoinWalletException("Coin Wallet not found with id: "+coinWalletId));
	}

	@Override
	public CoinWallet deleteCoinWallet(Integer coinWalletId) throws CoinWalletException, UserException {
		CoinWallet coinWallet = getCoinWallet(coinWalletId);
		if(coinWallet==null) {
			throw new CoinWalletException("Coin Wallet was not found with coin Wallet Id: "+coinWalletId);
		}
		repository.deleteById(coinWalletId);
		return coinWallet;
	}

	@Override
	public CoinWallet deleteCryptoCoinsFromWalletByQuantity(SellingCoinFromWalletDto dto)
			throws CoinException, CoinWalletException, UserException {
		return null;
	}

	@Override
	public CoinWallet deleteCryptoCoinsFromWalletByAmount(SellingCoinFromWalletDto dto)
			throws CoinException, CoinWalletException, UserException {
		return null;
	}

	@Override
	public CoinWallet addCoinWallet(Integer userId) throws CoinWalletException, UserException {
		if(getCoinWalletByUserId(userId)!=null) {
			throw new CoinWalletException("Coin Wallet is already there in Db with userId: "+userId);
		}
		
		if(userExternalService.getOnlyUser(userId)==null) {
			throw new UserException("User was not found with id: "+userId);
		}
		
		CoinWallet coinWallet = new CoinWallet();
		coinWallet.setUserId(userId);
		List<Integer> coins = new ArrayList<>();
		coins.add(0);
		coinWallet.setCoins(coins);
		coinWallet.setInvestedAmount(0.0);
		coinWallet.setProfit(0.0);
		coinWallet.setTotalCryptoValue(0.0);
		return repository.save(coinWallet);
	}

}

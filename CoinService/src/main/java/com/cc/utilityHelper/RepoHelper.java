package com.cc.utilityHelper;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cc.entites.Coins;
import com.cc.repository.CoinRepository;

@Component
public class RepoHelper {
	
	@Autowired
	CoinRepository repository;
	
	public String[] getNullPropertyNames(Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

		Set<String> emptyNames = new HashSet<>();
		for (java.beans.PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null)
				emptyNames.add(pd.getName());
		}

		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}  
	
	public boolean checkIfCoinIsThere(String name) {
		Coins coin = repository.findCoinsByCryptoName(name);
		return coin!=null?false:true;
		
	}
	

}

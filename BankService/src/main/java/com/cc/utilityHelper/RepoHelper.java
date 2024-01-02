package com.cc.utilityHelper;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.cc.entity.Bank;
import com.cc.repository.BankRepository;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class RepoHelper {
	
	@Autowired
	BankRepository repository;
	
	@Autowired
	JwtService jwtService;
	
	public String getUsernameFromToken(HttpServletRequest request) {
		Enumeration<String> authHeader = request.getHeaders(HttpHeaders.AUTHORIZATION);
        String token = (authHeader.nextElement());
        if (token != null && token.startsWith("Bearer ")) {
        	token = token.substring(7);
      }
        String username = jwtService.extractUsername(token);
        return username;
	}
	
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
	
	public boolean checkIfBankIsThere(Long bankAccountNumber) {
		Bank bank = repository.findByBankAccountNumber(bankAccountNumber);	
		return (bank != null) ? false : true;

	}
	
	public boolean checkIfBankIsThereByBankId(Integer id) {
		Bank bank = repository.getById(id);	
		return (bank != null) ? true : false;

	}	
	

}

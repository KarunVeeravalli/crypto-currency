package com.cc.util;

import java.time.LocalDate;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class RepoHelper {
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
	
	public String getOtp() {
		Integer upper = 999999;
		Integer lower = 111111;
		Integer otp = (int) (Math.random() * (upper - lower)) + lower;
		return otp.toString();
	}
	
	public String getOtpAndMsg(String otp) {
		
		System.out.println(otp);
		String body = "Dear Customer,\n"
				+ "\n"
				+ "\n"
				+ "\n"
				+ "\n"
				+ "\n"
				+" "   + otp+" is your SECRET OTP for Accessing our Coin Currency at Browser on "+ LocalDate.now() +",  OTP valid for 5 mins.Pls do not share OTP with anyone.\n"
				+ "\n"
				+ "In case you have any queries / clarifications, please call us at our Customer Service number :\n"
				+ "\n"
				+ "8340018900\n"
				+ "8340018900\n"
				+ "\n"
				+ "\n"
				+ "\n"
				+ "\n"
				+ "\n"
				+ "\n"+" Thank You For Using our Coin Currency :) ";
		
		return body;
	}
	
	

}

package com.cc.exception;

public class CoinException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public CoinException() {
		
	}
	
	public CoinException(String msg) {
		super(msg);
	}
}

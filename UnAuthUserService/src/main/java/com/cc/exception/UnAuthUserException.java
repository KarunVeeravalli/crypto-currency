package com.cc.exception;

public class UnAuthUserException extends Exception {

	private static final long serialVersionUID = 1L;

	public UnAuthUserException() {
		super();
	}

	public UnAuthUserException(String message) {
		super(message);
	}

}

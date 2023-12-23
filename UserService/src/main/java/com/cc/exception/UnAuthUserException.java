package com.cc.exception;

public class UnAuthUserException extends Exception {

	public UnAuthUserException() {
		super();
	}

	public UnAuthUserException(String message) {
		super(message);
	}

	private static final long serialVersionUID = 1L;

}

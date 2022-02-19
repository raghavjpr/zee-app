package com.zee.zee5app.exception;

import lombok.ToString;

@ToString(callSuper = true)
public class InvalidAmountException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1589558864668723788L;

	public InvalidAmountException(String message) {
		// TODO Auto-generated constructor stub
		super(message);
	}

}

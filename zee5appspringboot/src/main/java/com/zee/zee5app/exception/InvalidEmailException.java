package com.zee.zee5app.exception;

import lombok.ToString;

@ToString(callSuper = true)

public class InvalidEmailException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1092008222695792486L;

	public InvalidEmailException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}

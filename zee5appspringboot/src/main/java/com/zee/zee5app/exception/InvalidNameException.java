package com.zee.zee5app.exception;

import lombok.ToString;

@ToString(callSuper = true)

public class InvalidNameException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7629807990099867555L;

	public InvalidNameException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}

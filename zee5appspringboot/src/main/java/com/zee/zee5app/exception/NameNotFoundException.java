package com.zee.zee5app.exception;

import lombok.ToString;

@ToString(callSuper = true)

public class NameNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5801138600054969154L;

	public NameNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}

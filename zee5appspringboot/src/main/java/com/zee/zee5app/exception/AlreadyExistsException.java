package com.zee.zee5app.exception;

import lombok.ToString;

@ToString(callSuper = true)
public class AlreadyExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8314475980501498412L;

	public AlreadyExistsException(String message) {
		super(message);
	}

}

package com.zee.zee5app.exception;

import lombok.ToString;

@ToString(callSuper = true)
public class IdNotFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5623590102697744186L;

	public IdNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}

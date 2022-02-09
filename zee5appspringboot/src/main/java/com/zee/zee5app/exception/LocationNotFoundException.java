package com.zee.zee5app.exception;

import lombok.ToString;

@ToString(callSuper = true)
public class LocationNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4543669394927692330L;

	public LocationNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}

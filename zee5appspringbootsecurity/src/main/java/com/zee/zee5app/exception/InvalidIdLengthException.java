package com.zee.zee5app.exception;

import lombok.ToString;

@ToString(callSuper = true)
// toString from the base Class ===> Exception class

public class InvalidIdLengthException extends Exception {
	// This a user defined Exception

	/**
	 * 
	 */
	private static final long serialVersionUID = -4088807692487117026L;

	public InvalidIdLengthException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

//	@Override
//	public String toString() {
//		return "InvalidIdLengthException [toString()=" + super.toString() + "]";
//	}

}

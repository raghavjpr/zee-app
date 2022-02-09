package com.zee.zee5app.exception;

import lombok.ToString;

@ToString(callSuper = true)
// toString from the base Class ===> Exception class

public class InvalidIdLengthException extends Exception {
	// This a user defined Exception

	public InvalidIdLengthException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

//	@Override
//	public String toString() {
//		return "InvalidIdLengthException [toString()=" + super.toString() + "]";
//	}

}

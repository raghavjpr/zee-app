package com.zee.zee5app.exception.apierror;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false) // it will not call super.hashcode or super.equals
@AllArgsConstructor
@NoArgsConstructor
public class ApiValidationError extends ApiSubError {

	private String object;
	private String field;
	private Object rejectedValue;
	private String message;

	public ApiValidationError(String object, String message) {
		this.object = object;
		this.message = message;
	}
}

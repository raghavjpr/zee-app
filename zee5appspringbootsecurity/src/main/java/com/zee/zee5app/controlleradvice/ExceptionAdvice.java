package com.zee.zee5app.controlleradvice;

import java.util.HashMap;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.zee.zee5app.exception.AlreadyExistsException;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.apierror.ApiError;

@ControllerAdvice
public class ExceptionAdvice extends ResponseEntityExceptionHandler {
	// this class should be used when any user defined exception is called through
	// out all the controller

	@ExceptionHandler(AlreadyExistsException.class)
	public ResponseEntity<?> alreadyRecordExistsExceptionHandler(AlreadyExistsException e) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("message", "Record Already Exists: " + e.getMessage());
		return ResponseEntity.badRequest().body(map);
	}

//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<?> exceptionHandler(Exception e) {
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("message", "Unknown Exception: " + e.getMessage());
//		return ResponseEntity.badRequest().body(map);
//	}

	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<?> idNotFoundExceptionHandler(IdNotFoundException e) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("message", "No Record with such Id: " + e.getMessage());
		return ResponseEntity.badRequest().body(map);
	}

	/*
	 * @Valid should be customized custom error details object with subError (field
	 * error) create the beans prepare the list and methods
	 */
//	@Override
//	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//			HttpHeaders headers, HttpStatus status, WebRequest request) {
//		// TODO Auto-generated method stub
//		return ResponseEntity.ok("Validation Error");
//	}
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
		apiError.setMessage("Validation Error");
		apiError.addValidationErrors(ex.getBindingResult().getFieldErrors());
		apiError.addValidationError(ex.getBindingResult().getGlobalErrors());
		return builResponseEntity(apiError);

	}

	private ResponseEntity<Object> builResponseEntity(ApiError apiError) {
		// to get response entity object ==> if i want to name any changes into our
		// existing object then in every return we have to do the change instead of that
		// if we will use build response entity method ==> ease of maintenance
		return new ResponseEntity<Object>(apiError, apiError.getHttpStatus());

	}

	@ExceptionHandler(ConstraintViolationException.class)
	protected ResponseEntity<?> handleConstraintViolation() {
		return null;
	}
}

package com.military.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidInputException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public InvalidInputException(String message, Exception ex) {
		super(message, ex);
	}
	
	public InvalidInputException(String message) {
		super(message);
	}
	
	public InvalidInputException() {
		super();
	}
}

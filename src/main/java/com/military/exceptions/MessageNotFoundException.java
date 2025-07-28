package com.military.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MessageNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public MessageNotFoundException(String message, Exception ex) {
		super(message, ex);
	}
	
	public MessageNotFoundException(String message) {
		super(message);
	}
	
	public MessageNotFoundException() {
		super();
	}
}

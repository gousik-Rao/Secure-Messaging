package com.military.exceptions;

public class UserExistsException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public UserExistsException() {
		super();
	}
	
	public UserExistsException(String message) {
		super(message);
	}
	
	public UserExistsException(String message, Exception error) {
		super(message, error);
	}
}

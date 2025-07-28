package com.military.exceptions;

public class SignatureException extends RuntimeException{
	
	public static final long serialVersionUID = 1L;

	public SignatureException() {
		super();
	}
	
	public SignatureException(String message) {
		super(message);
	}
	
	public SignatureException(String message, Exception ex) {
		super(message, ex);
	}
}

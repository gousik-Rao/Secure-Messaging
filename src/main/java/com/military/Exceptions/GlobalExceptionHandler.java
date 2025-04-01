package com.military.Exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.military.DTO.ErrorResponseDTO;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<?> handleUserNotFound(UserNotFoundException ex) {
		return buildErrorResponse(HttpStatus.NOT_FOUND, "User Not Found", ex.getMessage());
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGlobalException(Exception ex){
		return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error occurred", ex.getMessage());
	}
	
	@ExceptionHandler(InvalidInputException.class)
	public ResponseEntity<?> handleInvalidInput(InvalidInputException ex){
		return buildErrorResponse(HttpStatus.BAD_REQUEST, "Invalid Input", ex.getMessage());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex){
		String errorMessage = ex.getBindingResult().getFieldError().getDefaultMessage();
		return buildErrorResponse(HttpStatus.BAD_REQUEST, "Validation Error", errorMessage);
	}
	
	@ExceptionHandler(MessageNotFoundException.class)
	public ResponseEntity<?> handleMessageNotFound(MessageNotFoundException ex){
		return buildErrorResponse(HttpStatus.NOT_FOUND, "Message not found", ex.getMessage());
	}
	
	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<?> handleInvalidCredentials(InvalidCredentialsException ex){
		return buildErrorResponse(HttpStatus.UNAUTHORIZED, "Invalid Credentials", ex.getMessage());
	}
	
	@ExceptionHandler(UserExistsException.class)
	public ResponseEntity<?> handleUserExists(UserExistsException ex){
		return buildErrorResponse(HttpStatus.BAD_REQUEST, "User already exists", ex.getMessage());
	}
	
	@ExceptionHandler(SignatureException.class)
	public ResponseEntity<?> handleSignatureException(SignatureException ex){
		return buildErrorResponse(HttpStatus.UNAUTHORIZED, "Invalid JWT Signature", ex.getMessage());
	}
	
	@ExceptionHandler(MalformedJwtException.class)
	public ResponseEntity<?> handleMalformedJwtException(MalformedJwtException ex){
		return buildErrorResponse(HttpStatus.BAD_REQUEST, "Invalid JWT format", ex.getMessage());
	}
	
	@ExceptionHandler(ExpiredJwtException.class)
	public ResponseEntity<?> handleExpiredJwtException(ExpiredJwtException ex){
		return buildErrorResponse(HttpStatus.UNAUTHORIZED, "Token has expired, Please login again", ex.getMessage());
	}
	
	@ExceptionHandler(JwtException.class)
	public ResponseEntity<?> handleJwtException(JwtException ex){
		return buildErrorResponse(HttpStatus.UNAUTHORIZED, "Invalid token", ex.getMessage());
	}
	
	
//	Utility method
	private ResponseEntity<ErrorResponseDTO> buildErrorResponse(HttpStatus status, String error, String message){
		return ResponseEntity.status(status).body(new ErrorResponseDTO(LocalDateTime.now(), status.value(), error, message));            
	}
}



















package com.hk.pm.accounts.common.exception;

import java.nio.file.AccessDeniedException;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Vineeth Kiv
 * Global exception handler for account service
 *
 */
@ControllerAdvice
public class AccountServiceExceptionHandler extends ResponseEntityExceptionHandler {

	private final Logger logger = LoggerFactory.getLogger(AccountServiceExceptionHandler.class);
	
	
	@ExceptionHandler({ AccessDeniedException.class })
	public ResponseEntity<Object> handleAccessDeniedException(Exception ex, WebRequest request) {
	        return new ResponseEntity<Object>(
	          "Access denied.", new HttpHeaders(), HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(value = { IllegalArgumentException.class })
	protected ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException ex, WebRequest request) {
		
		logger.error("IllegalArgumentException", ex);
		
		String bodyOfResponse = "{ \"error\": " + ex.getMessage() + " \" }";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(value = { NoSuchElementException.class })
	protected ResponseEntity<Object> handleNoSuchElement(NoSuchElementException ex, WebRequest request) {
		
		logger.error("NoSuchElementException", ex);
		
		String bodyOfResponse = "{ \"error\": " + ex.getMessage() + "\" }";
		//return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
		 return new ResponseEntity<Object>(
				 bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = { Exception.class })
	protected ResponseEntity<Object> handleConflict(Exception ex, WebRequest request) {
		
		logger.error("General Service Error", ex);
		
		String bodyOfResponse = "{ \"error\": " + ex.getMessage() + "\" }";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}

}

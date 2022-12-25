package com.authentication.nubnasirauth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnAuthorizedException extends RuntimeException {
	public UnAuthorizedException(String message) {
		super(message);
	}
	
	public UnAuthorizedException(String message, Exception e) {
		super(message, e);
	}
}

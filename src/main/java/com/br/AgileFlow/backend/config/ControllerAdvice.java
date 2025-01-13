package com.br.AgileFlow.backend.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.br.AgileFlow.backend.controller.exceptions.PasswordValidationError;

@RestControllerAdvice
public class ControllerAdvice {
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(PasswordValidationError.class)
	public String handlerPasswordError(PasswordValidationError exception) {
		return exception.getDescription();
	}

}

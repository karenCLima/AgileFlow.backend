package com.br.AgileFlow.backend.config;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.FieldError;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.br.AgileFlow.backend.controller.exceptions.PasswordValidationError;

import io.swagger.v3.oas.annotations.Hidden;

//@Hidden
@RestControllerAdvice
public class ControllerAdvice {
	
	@Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<PasswordValidationError> handler(MethodArgumentNotValidException exception){
         List<PasswordValidationError> errors = new ArrayList<>();
         List<FieldError> fieldErros = exception.getBindingResult().getFieldErrors();

         fieldErros.forEach( e -> {
             String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
             PasswordValidationError validationError =  new PasswordValidationError(message);
             errors.add(validationError);
         });

         return errors;
    }
	
//	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
//	@ExceptionHandler(PasswordValidationError.class)
//	public String handlerPasswordError(PasswordValidationError exception) {
//		return exception.getDescription();
//	}

}

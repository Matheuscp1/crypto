package com.crypto.controller;


import com.crypto.validation.GenericErrors;
import io.swagger.v3.oas.annotations.Hidden;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Hidden
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionControllerAdvice {
	private static final Logger log = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	GenericErrors handleNoHandlerFoundException(NoHandlerFoundException ex) {
		log.error(ex.getMessage());
		return new GenericErrors(ex.getMessage());
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	GenericErrors handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
		log.error(ex.getMessage());
		return new GenericErrors(ex.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public GenericErrors handleMethodNotValidException(MethodArgumentNotValidException ex) {
		List<String> errors = ex.getBindingResult().getAllErrors().stream().map(erro -> erro.getDefaultMessage())
				.collect(Collectors.toList());
		log.error(errors.toString());
		return new GenericErrors(errors);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	GenericErrors handleIllegalArgumentException(IllegalArgumentException ex) {
		log.error(ex.getMessage());
		return new GenericErrors(ex.getMessage());
	}


}

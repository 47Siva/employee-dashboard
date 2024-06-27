package com.app.employeedashboard.advice;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.app.employeedashboard.dto.ErrorResponse;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {
		ErrorResponse errors = new ErrorResponse();
		errors.setErrorList((Stream.of(ex.getMessage().split(",")).collect(Collectors.toList())));
		errors.setReason(ex.getMessage());
		errors.setCode(HttpStatus.BAD_REQUEST.toString());
		return new ResponseEntity<>(errors, HttpStatus.CONFLICT);
	}
}

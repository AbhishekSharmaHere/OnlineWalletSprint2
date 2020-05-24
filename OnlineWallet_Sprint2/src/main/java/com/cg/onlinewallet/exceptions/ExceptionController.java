package com.cg.onlinewallet.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
	
	
	@ExceptionHandler(value = UnauthorizedAccessException.class)
	public ResponseEntity<String> handleNullException(UnauthorizedAccessException exception)
	{
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	
	@ExceptionHandler(value = InvalidException.class)
	public ResponseEntity<String> handleInvalidException(InvalidException exception) 
	{
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = ValidationException.class)
	public ResponseEntity<String> handleValidationException(ValidationException exception)
	{
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = WrongValueException.class)
	public ResponseEntity<String> handleWrongValueException(WrongValueException exception)
	{
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

}

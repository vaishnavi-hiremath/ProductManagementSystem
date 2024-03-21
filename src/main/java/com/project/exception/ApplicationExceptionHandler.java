package com.project.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.project.utility.ErrorStructure;

@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	private ErrorStructure errorStructure;
	
	public ApplicationExceptionHandler(ErrorStructure errorStructure) {
		super();
		this.errorStructure = errorStructure;
	}


	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		Map<String, String> messages = new HashMap<String, String>();
		List<ObjectError> errors = ex.getAllErrors();
		
		errors.forEach(error->{
			FieldError fe = (FieldError) error;
			messages.put(fe.getField(), error.getDefaultMessage());
		});
		
		return ResponseEntity.badRequest().body(errorStructure.setErrorCode(HttpStatus.BAD_REQUEST.value())
							 								  .setErrorMessage("Invalid Inputs!")
							 								  .setRootCause(messages));
		
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure> productNotFoundByIdException(ProductNotFoundByIdException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
							 .body(errorStructure.setErrorCode(HttpStatus.NOT_FOUND.value())
									 			 .setErrorMessage(e.getMessage())
									 			 .setRootCause("The Product you are looking for is no longer available"));
	}
}

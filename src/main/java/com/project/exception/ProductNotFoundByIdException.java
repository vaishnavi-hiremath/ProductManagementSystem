package com.project.exception;

public class ProductNotFoundByIdException extends RuntimeException {

	public ProductNotFoundByIdException(String message) {
		super(message);
	}
}

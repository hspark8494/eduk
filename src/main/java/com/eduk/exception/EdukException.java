package com.eduk.exception;

public class EdukException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public EdukException(){}
	
	public EdukException(String message) {
		super(message);
	}
	
}

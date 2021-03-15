package com.example.demo.exception;

import java.io.Serializable;

import javassist.SerialVersionUID;

public class APIException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public APIException(String message) {
		super(message);
	}

}

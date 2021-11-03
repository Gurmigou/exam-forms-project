package com.infpulse.studentspoll.exceptions;

public class NotFoundException extends RuntimeException {

	public NotFoundException(String objectName) {
		super(objectName + " not found ");
	}

}

package com.infpulse.studentspoll.exceptions;

public class NoPermissionException extends RuntimeException {

	public NoPermissionException(String objectName) {
		super("No permission to access " + objectName);
	}
}

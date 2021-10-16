package com.infpulse.studentspoll.exceptions;

public class UserAlreadyExistsException extends RegistrationException {
    public UserAlreadyExistsException() {
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}

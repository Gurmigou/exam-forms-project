package com.infpulse.studentspoll.exceptions;

public class ConfirmPasswordIsIncorrectException extends RegistrationException {
    public ConfirmPasswordIsIncorrectException() {
    }

    public ConfirmPasswordIsIncorrectException(String message) {
        super(message);
    }
}

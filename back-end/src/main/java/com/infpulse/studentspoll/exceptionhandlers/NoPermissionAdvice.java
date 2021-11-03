package com.infpulse.studentspoll.exceptionhandlers;

import com.infpulse.studentspoll.exceptions.NoPermissionException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NoPermissionAdvice {

	@ResponseBody
	@ExceptionHandler(NoPermissionException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public String formNotFoundHandler(NoPermissionException exception) {
		return exception.getMessage();
	}
}

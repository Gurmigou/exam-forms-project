package com.infpulse.studentspoll.exceptionhandlers;

import com.infpulse.studentspoll.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class NotFoundAdvice {

	@ResponseBody
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String formNotFoundHandler(NotFoundException exception) {
		return exception.getMessage();
	}
}

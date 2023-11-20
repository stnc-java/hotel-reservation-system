package com.aspire.webapp.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {
	@ExceptionHandler(ApplicationException.class)
	public String handleException() {
		return "error-page";
	}
}

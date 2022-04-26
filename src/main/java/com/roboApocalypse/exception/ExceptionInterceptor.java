package com.roboApocalypse.exception;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.InvalidMimeTypeException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionInterceptor extends ResponseEntityExceptionHandler {

	@ExceptionHandler(RobotApocalypse.class)
	public final ResponseEntity<Object> exceptionHandler(RobotApocalypse e) {
		return ResponseEntity.status(e.getCode()).body(e.getMessage());
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public final ResponseEntity<Object> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
		return ResponseEntity.status(405).body(String.format("'%s' should be a '%s' and your send '%s' isn't valid",
				ex.getName(), ex.getRequiredType().getSimpleName(), ex.getValue()));
	}
	

	@ExceptionHandler(InvalidMimeTypeException.class)
	public final void handleInvalidMimeTypeException(InvalidMimeTypeException ex) {
		
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String exString =ex.getLocalizedMessage();
		String error = exString.substring(exString.lastIndexOf("[")+1, exString.lastIndexOf("]"));
		error+=" "+exString.substring(exString.indexOf("InvalidFormatException: ")+24,exString.indexOf("at ["));
		return ResponseEntity.status(status).body(error);
	}

	@ExceptionHandler(MultipartException.class)
	public final ResponseEntity<Object> handleMultipartException(MultipartException e) {
		String error = e.getCause().getLocalizedMessage();
		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher(error);
		String number;
		while (m.find()) {
			number = m.group();
			error = error.replace(number, String.format("%.2f", Double.parseDouble(number) / 1000000) + " mb");

		}
	
		return ResponseEntity.status(405).body(error);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex) {
		return ResponseEntity.status(500).body(ex);
	}
}

package com.roboApocalypse.exception;

import java.util.ArrayList;
import java.util.List;

public class RobotApocalypse extends Exception {
	
	private static final long serialVersionUID = 1L;
	private int code = -1;
	private String developerMessage;
	private List<String> errors = new ArrayList<>();
	private int errorCodeValue = -1;
	public final static int SUCCESSFUL_REQUEST = 200;
	public final static int BAD_REQUEST = 400;
	public final static int NOT_FOUND = 404;
	
	
	public RobotApocalypse(Exception e) {
		super(e.getMessage());
	}

	public RobotApocalypse(int code, String message) {
		super(message);
		this.code = code;
	}

	public RobotApocalypse(int code, String message, String developerMessage) {
		super(message);
		this.code = code;
		this.developerMessage = developerMessage;
	}

	public RobotApocalypse(int code, String message, String messageDescription, int errorCodeValue) {
		super(message);
		this.code = code;
		this.developerMessage = messageDescription;
		this.errorCodeValue = errorCodeValue;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getDeveloperMessage() {
		return developerMessage;
	}
	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}
	public List<String> getErrors() {
		return errors;
	}
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	public int getErrorCodeValue() {
		return errorCodeValue;
	}
	public void setErrorCodeValue(int errorCodeValue) {
		this.errorCodeValue = errorCodeValue;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public static int getSuccessfulRequest() {
		return SUCCESSFUL_REQUEST;
	}
	
	public static int getNotFound() {
		return NOT_FOUND;
	}
	
}

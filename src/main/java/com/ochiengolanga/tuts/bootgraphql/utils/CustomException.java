package com.ochiengolanga.tuts.bootgraphql.utils;

public class CustomException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 7636572282547061382L;

	private int errorCode;
	private String errorMessage;

	public CustomException(int errorCode) {
		super(ExceptionUtils.getMessage(errorCode));
		this.errorCode = errorCode;
		this.errorMessage = ExceptionUtils.getMessage(errorCode);
	}

	public CustomException(String errorMessage, int errorCode) {
		super(errorMessage);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public CustomException(Throwable t) {
		super(t);
	}

	public CustomException(int errorCode, Throwable t) {
		super(t);
		this.errorCode = errorCode;
		this.errorMessage = ExceptionUtils.getMessage(errorCode);
	}

	public CustomException(String errorMessage, int errorCode, Throwable t) {
		super(t);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public int getErrorCode() {
		return errorCode;
	}

	@Override
	public String getMessage() {
		return getErrorMessage();
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}

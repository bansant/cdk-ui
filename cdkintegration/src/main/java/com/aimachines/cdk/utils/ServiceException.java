package com.aimachines.cdk.utils;

import org.slf4j.event.Level;
import org.slf4j.helpers.MessageFormatter;


public class ServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	private final ErrorCode error;

	private Level logLevel = Level.ERROR;

	/**
	 * Constructor
	 * 
	 * @param message
	 */
	public ServiceException(String message) {
		this(ErrorCode.UNCLASSIFIED, message);
	}

	/**
	 * Constructor
	 * 
	 * @param error
	 * @param message
	 */
	public ServiceException(ErrorCode error, String message) {
		super(message);
		this.error = error;
	}

	/**
	 * Constructor
	 * 
	 * @param error
	 * @param format
	 * @param arguments
	 */
	public ServiceException(ErrorCode error, String format, Object... arguments) {
		super(MessageFormatter.arrayFormat(format, arguments).getMessage());
		this.error = error;
	}

	/**
	 * Constructor
	 * 
	 * @param cause
	 */
	public ServiceException(Throwable cause) {
		this(ErrorCode.UNCLASSIFIED, cause);
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 * @param cause
	 */
	public ServiceException(String message, Throwable cause) {
		this(ErrorCode.UNCLASSIFIED, message, cause);
	}

	/**
	 * Constructor
	 * 
	 * @param error
	 * @param cause
	 */
	public ServiceException(ErrorCode error, Throwable cause) {
		this(error, cause.getMessage(), cause);
	}

	/**
	 * Constructor
	 * 
	 * @param error
	 * @param message
	 * @param cause
	 */
	public ServiceException(ErrorCode error, String message, Throwable cause) {
		super(message, cause);
		this.error = error;
	}

	/**
	 * Constructor
	 * 
	 * @param error
	 * @param format
	 * @param arguments
	 * @param cause
	 */
	public ServiceException(ErrorCode error, String format, Object[] arguments, Throwable cause) {
		super(MessageFormatter.arrayFormat(format, arguments).getMessage(), cause);
		this.error = error;
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 * @param logLevel
	 */
	public ServiceException(String message, Level logLevel) {
		this(message);
		this.logLevel = logLevel;
	}

	/**
	 * Constructor
	 * 
	 * @param cause
	 * @param logLevel
	 */
	public ServiceException(Throwable cause, Level logLevel) {
		this(cause);
		this.logLevel = logLevel;
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 * @param cause
	 * @param logLevel
	 */
	public ServiceException(String message, Throwable cause, Level logLevel) {
		this(message, cause);
		this.logLevel = logLevel;
	}

	/**
	 * Returns the underlying error code.
	 * 
	 * @return the error code
	 */
	public ErrorCode getErrorCode() {
		return error;
	}

	/**
	 * Returns the expected log level for the message.
	 * 
	 * @return the log level ({@link Level#ERROR ERROR} by Default)
	 */
	public Level getLogLevel() {
		return logLevel;
	}

	/**
	 * Returns the message.
	 * 
	 * @return the message with root cause
	 */
	@Override
	public String getMessage() {
		String message = String.format("%s %s - %s", error.value(), error.reason(), super.getMessage());

		if (this.getCause() != null)
			message = String.format("%s%n\tRoot Cause: %s", message, this.getCause().getMessage());

		return message;
	}

}

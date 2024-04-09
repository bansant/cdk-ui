package com.aimachines.cdk.utils;


public enum ErrorCode {

	/** 100 Illegal Argument */
	ILLEGAL_ARGUMENT(100, "Illegal Argument"),

	/** 101 No Such Element */
	NO_SUCH_ELEMENT(101, "No Such Element"),

	/** 102 Index Out of Bounds */
	INDEX_OUT_OF_BOUNDS(102, "Index Out of Bounds"),

	/** 200 XML Error */
	XML_ERROR(200, "XML Error"),

	/** 201 XML Parser Error */
	XML_PARSER_ERROR(201, "XML Parser Error"),

	/** 202 XML Transformer Error */
	XML_TRANSFORMER_ERROR(202, "XML Transformer Error"),

	/** 300 Persistence Error */
	PERSISTENCE_ERROR(300, "Persistence Error"),

	/** 301 No Result */
	NO_RESULT(301, "No Result"),

	/** 400 Bad Request */
	BAD_REQUEST(400, "Bad Request"),

	/** 403 Forbidden Access */
	FORBIDDEN(403, "Forbidden Access"),

	/** 500 Internal Server Error */
	INTERNAL_SERVER_ERROR(500, "Internal Server Error"),

	/** 501 Algorithm Error */
	ALGO_ERROR(501, "Algorithm Error"),

	/** 700 JMS Error */
	JMS_ERROR(700, "JMS Error"),

	/** 701 JMS Connection Error */
	JMS_CONNECTION_ERROR(701, "JMS Connection Error"),

	/** 702 Invalid JMS Property */
	INVALID_JMS_PROPERTY(702, "Invalid JMS Property"),

	/** 900 Unknown Error */
	UNCLASSIFIED(900, "Unknown Error");

	private Integer value;

	private String reason;

	/**
	 * Creates a new error code enumeration.
	 * 
	 * @param value  the error code value
	 * @param reason the error code reason message
	 */
	private ErrorCode(Integer value, String reason) {
		this.value = value;
		this.reason = reason;
	}

	/**
	 * Returns the error code enum constant with the specified value.
	 * 
	 * @param value the constant value
	 * @return the error code enum constant
	 * @throws ServiceException if the enum has no constant with the specified
	 *                          value, or the specified value is null
	 */
	public static ErrorCode fromValue(Integer value) throws ServiceException {
		if (value == null)
			throw new ServiceException(ErrorCode.NO_SUCH_ELEMENT, "Error Code - The value is null");

		for (ErrorCode e : ErrorCode.values()) {
			if (e.value.equals(value))
				return e;
		}

		throw new ServiceException(ErrorCode.NO_SUCH_ELEMENT,
				"Error Code - No enum constant for the value '" + value + "'");
	}

	/**
	 * Returns the mapped error code value.
	 * 
	 * @return the error code value
	 */
	public Integer value() {
		return value;
	}

	/**
	 * Returns the mapped error code reason message.
	 * 
	 * @return the error code reason
	 */
	public String reason() {
		return reason;
	}

}

package com.trendyol.webservice.exception.restapi;

public class RestApiErrorMessages {

	public static final String VALIDATION_ERROR = "VALIDATION_ERROR";
	public static final String DATABASE_ERROR = "DATABASE_ERROR";
	public static final String ENTITY_NOT_FOUND = "ENTITY_NOT_FOUND";
	public static final String UNEXPECTED_ERROR = "UNEXPECTED_ERROR";
	public static final String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";
	public static final String MISSING_PARAMETER_MESSAGE = " parameter is missing";
	public static final String NOT_FOUND_MESSAGE = "Could not find the %s method for URL %s";
	public static final String MISMATCH_MESSAGE = "The parameter '%s' of value '%s' could not be converted to type '%s'";

	private RestApiErrorMessages() {

	}
}

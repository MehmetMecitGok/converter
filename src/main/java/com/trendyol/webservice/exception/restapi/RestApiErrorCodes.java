package com.trendyol.webservice.exception.restapi;

public class RestApiErrorCodes {

	public static final String DATABASE_ERROR_CODE = "1001";
	public static final String ENTITY_NOT_FOUND_CODE = "1002";
	public static final String MISSING_PARAMETER_ERROR_CODE = "1003";
	public static final String MISSING_PATH_VARIABLE_ERROR_CODE = "1004";
	public static final String MISSING_PATH_ERROR_CODE = "1005";
	public static final String TYPE_MISMATCH_ERROR_CODE = "1006";
	public static final String INTERNAL_SERVER_ERROR_CODE = "1007";
	public static final String CONVERTER_ERROR = "1008";

	public static final String UNEXPECTED_ERROR_CODE = "9999";

	private RestApiErrorCodes() {

	}
}

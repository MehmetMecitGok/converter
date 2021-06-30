package com.trendyol.webservice.exception.restapi;

import java.io.Serializable;

public class ApiValidationError implements Serializable {

	private static final long serialVersionUID = 6538920904590892758L;

	private String object;
	private String field;
	private Object rejectedValue;
	private Object expectedValue;
	private String subErrorCode;

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public Object getRejectedValue() {
		return rejectedValue;
	}

	public void setRejectedValue(Object rejectedValue) {
		this.rejectedValue = rejectedValue;
	}

	public Object getExpectedValue() {
		return expectedValue;
	}

	public void setExpectedValue(Object expectedValue) {
		this.expectedValue = expectedValue;
	}

	public String getSubErrorCode() {
		return subErrorCode;
	}

	public void setSubErrorCode(String subErrorCode) {
		this.subErrorCode = subErrorCode;
	}
}

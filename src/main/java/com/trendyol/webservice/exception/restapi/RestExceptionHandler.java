package com.trendyol.webservice.exception.restapi;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import javax.persistence.EntityNotFoundException;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.trendyol.webservice.exception.ConverterException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String message = ex.getParameterName() + RestApiErrorMessages.MISSING_PARAMETER_MESSAGE;
		return buildResponseEntity(
				new ApiError(BAD_REQUEST, message, ex, RestApiErrorCodes.MISSING_PARAMETER_ERROR_CODE));
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ApiError apiError = new ApiError(BAD_REQUEST);
		apiError.setMessage(RestApiErrorMessages.VALIDATION_ERROR);
		apiError.addValidationErrors(ex.getBindingResult().getFieldErrors());
		apiError.addValidationError(ex.getBindingResult().getGlobalErrors());
		return buildResponseEntity(apiError);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
		return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, RestApiErrorMessages.ENTITY_NOT_FOUND, ex,
				RestApiErrorCodes.ENTITY_NOT_FOUND_CODE));
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String message = ex.getVariableName() + RestApiErrorMessages.MISSING_PARAMETER_MESSAGE;
		return buildResponseEntity(
				new ApiError(HttpStatus.BAD_REQUEST, message, ex, RestApiErrorCodes.MISSING_PATH_VARIABLE_ERROR_CODE));
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String message = String.format(RestApiErrorMessages.NOT_FOUND_MESSAGE, ex.getHttpMethod(), ex.getRequestURL());
		return buildResponseEntity(
				new ApiError(HttpStatus.BAD_REQUEST, message, ex, RestApiErrorCodes.MISSING_PATH_ERROR_CODE));
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	protected ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex,
			WebRequest request) {
		if (ex.getCause() instanceof ConstraintViolationException) {
			return buildResponseEntity(new ApiError(HttpStatus.CONFLICT, RestApiErrorMessages.DATABASE_ERROR,
					ex.getCause(), RestApiErrorCodes.DATABASE_ERROR_CODE));
		}
		return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,
				RestApiErrorMessages.INTERNAL_SERVER_ERROR, ex, RestApiErrorCodes.INTERNAL_SERVER_ERROR_CODE));
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
			WebRequest request) {
		String message = String.format(RestApiErrorMessages.MISMATCH_MESSAGE, ex.getName(), ex.getValue(),
				ex.getRequiredType().getSimpleName());
		return buildResponseEntity(
				new ApiError(HttpStatus.BAD_REQUEST, message, ex, RestApiErrorCodes.TYPE_MISMATCH_ERROR_CODE));
	}

	@ExceptionHandler(ConverterException.class)
	protected ResponseEntity<Object> handleLfDataReadException(ConverterException ex) {
		String message = ex.getMessage();
		return buildResponseEntity(
				new ApiError(HttpStatus.BAD_REQUEST, message, ex, RestApiErrorCodes.CONVERTER_ERROR));
	}

	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

}

package com.swabhav.demo.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Map<String, Object>> handleResourceNotFound(ResourceNotFoundException ex) {
		return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
	}

	@ExceptionHandler(DuplicateResourceException.class)
	public ResponseEntity<Map<String, Object>> handleDuplicateResource(DuplicateResourceException ex) {
		return buildResponse(HttpStatus.CONFLICT, ex.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
		Map<String, Object> errorBody = new HashMap<>();
		errorBody.put("timestamp", LocalDateTime.now());
		errorBody.put("status", HttpStatus.BAD_REQUEST.value());
		errorBody.put("error", "Bad Request");
		List<String> messages = ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
				.collect(Collectors.toList());
		errorBody.put("messages", messages);
		return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Map<String, Object>> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
		return buildResponse(HttpStatus.BAD_REQUEST, "Invalid parameter type");
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Map<String, Object>> handleInvalidJson(HttpMessageNotReadableException ex) {
		return buildResponse(HttpStatus.BAD_REQUEST, "Invalid JSON format");
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Map<String, Object>> handleDataIntegrity(DataIntegrityViolationException ex) {
		return buildResponse(HttpStatus.CONFLICT, "Data integrity violation");
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<Map<String, Object>> handleAccessDenied(AccessDeniedException ex) {
		return buildResponse(HttpStatus.FORBIDDEN, "Access denied");
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Map<String, Object>> handleIllegalArgument(IllegalArgumentException ex) {
		return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, Object>> handleGeneric(Exception ex) {
		return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
	}

	private ResponseEntity<Map<String, Object>> buildResponse(HttpStatus status, String message) {
		Map<String, Object> errorBody = new HashMap<>();
		errorBody.put("timestamp", LocalDateTime.now());
		errorBody.put("status", status.value());
		errorBody.put("error", status.getReasonPhrase());
		errorBody.put("message", message);
		return new ResponseEntity<>(errorBody, status);
	}
}
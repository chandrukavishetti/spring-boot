package com.chandru.demo.Exception;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice // this annotaion is going to tell that this is the globle exception class
public class GlobalExceptionHandler {

	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<Map<String, Object>> handleStudentNotFoundException(StudentNotFoundException ex) {

		Map<String, Object> errorBody = new HashMap<>();

		errorBody.put("TimeStamp", LocalDate.now());
		errorBody.put("status", HttpStatus.NOT_FOUND.value());
		errorBody.put("error", "Not Found");
		errorBody.put("message", ex.getMessage());

		return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Map<String, Object>> handleTypeMismatchException(MethodArgumentTypeMismatchException ex) {

		Map<String, Object> errorBody = new HashMap<>();

		errorBody.put("Timestamp", LocalDateTime.now());
		errorBody.put("status", HttpStatus.BAD_REQUEST.value());
		errorBody.put("error", "Bad Request");
		errorBody.put("message", "Invalid Id. Id must be a number");

		return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Map<String, Object>> handleHttpMessageNotReadableException(
			HttpMessageNotReadableException ex) {

		Map<String, Object> errorBody = new HashMap<>();

		errorBody.put("Timestamp", LocalDateTime.now());
		errorBody.put("status", HttpStatus.BAD_REQUEST.value());
		errorBody.put("error", "Bad Request");
		errorBody.put("message", "Invalid data type entered");

		return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
	}


}
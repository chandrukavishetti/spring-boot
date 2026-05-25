package com.chandru.demo.Exception;

public class StudentNotFoundException extends RuntimeException {
	public StudentNotFoundException(int id) {
		super("student not found with id : " + id);
	}

}

package com.swabhav.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.swabhav.demo.dto.PageResponseDto;
import com.swabhav.demo.dto.StudentRequestDto;
import com.swabhav.demo.dto.StudentResponseDto;
import com.swabhav.demo.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
@SecurityRequirement(name = "basicAuth")
public class StudentController {

	private final StudentService studentService;

	@Operation(summary = "Create student with profile")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public StudentResponseDto createStudent(@Valid @RequestBody StudentRequestDto requestDto) {
		return studentService.createStudent(requestDto);
	}

	@Operation(summary = "Get all students")
	@GetMapping
	public List<StudentResponseDto> getAllStudents() {
		return studentService.getAllStudents();
	}

	@Operation(summary = "Get students with pagination")
	@GetMapping("/page")
	public PageResponseDto<StudentResponseDto> getAllStudentsWithPagination(
			@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "5") int pageSize) {
		return studentService.getAllStudentsWithPagination(pageNumber, pageSize);
	}

	@Operation(summary = "Get student by ID")
	@GetMapping("/{id}")
	public StudentResponseDto getStudentById(@PathVariable Long id) {
		return studentService.getStudentById(id);
	}

	@Operation(summary = "Update student with profile")
	@PutMapping("/{id}")
	public StudentResponseDto updateStudent(@PathVariable Long id, @Valid @RequestBody StudentRequestDto requestDto) {
		return studentService.updateStudent(id, requestDto);
	}

	@Operation(summary = "Delete student")
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteStudent(@PathVariable Long id) {
		studentService.deleteStudent(id);
	}
}
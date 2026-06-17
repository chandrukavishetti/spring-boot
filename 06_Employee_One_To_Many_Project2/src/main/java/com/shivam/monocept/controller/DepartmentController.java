package com.shivam.monocept.controller;

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

import com.shivam.monocept.dto.DepartmentRequestDto;
import com.shivam.monocept.dto.DepartmentResponseDto;
import com.shivam.monocept.dto.PageResponseDto;
import com.shivam.monocept.service.DepartmentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {

	private final DepartmentService departmentService;

	// API 1: Create Department
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public DepartmentResponseDto createDepartment(@Valid @RequestBody DepartmentRequestDto requestDto) {

		return departmentService.createDepartment(requestDto);
	}

	// API 2: Get All Departments
	@GetMapping
	public List<DepartmentResponseDto> getAllDepartments() {

		return departmentService.getAllDepartments();
	}

	// API 3: Get Departments With Pagination
	@GetMapping("/page")
	public PageResponseDto<DepartmentResponseDto> getAllDepartmentsWithPagination(

			@RequestParam(defaultValue = "0") int pageNumber,

			@RequestParam(defaultValue = "5") int pageSize) {

		return departmentService.getAllDepartmentsWithPagination(pageNumber, pageSize);
	}

	// API 4: Get Department By ID
	@GetMapping("/{id}")
	public DepartmentResponseDto getDepartmentById(@PathVariable Long id) {

		return departmentService.getDepartmentById(id);
	}

	// API 5: Update Department
	@PutMapping("/{id}")
	public DepartmentResponseDto updateDepartment(@PathVariable Long id,
			@Valid @RequestBody DepartmentRequestDto requestDto) {

		return departmentService.updateDepartment(id, requestDto);
	}

	// API 6: Delete Department
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteDepartment(@PathVariable Long id) {

		departmentService.deleteDepartment(id);
	}
}
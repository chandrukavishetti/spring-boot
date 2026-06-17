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

import com.swabhav.demo.dto.DepartmentRequestDto;
import com.swabhav.demo.dto.DepartmentResponseDto;
import com.swabhav.demo.dto.PageResponseDto;
import com.swabhav.demo.service.DepartmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class DepartmentController {

	private final DepartmentService departmentService;

	@Operation(summary = "Create department with employees")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public DepartmentResponseDto createDepartment(@Valid @RequestBody DepartmentRequestDto requestDto) {
		return departmentService.createDepartment(requestDto);
	}

	@Operation(summary = "Get all departments")
	@GetMapping
	public List<DepartmentResponseDto> getAllDepartments() {
		return departmentService.getAllDepartments();
	}

	@Operation(summary = "Get departments with pagination")
	@GetMapping("/page")
	public PageResponseDto<DepartmentResponseDto> getAllDepartmentsWithPagination(
			@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "5") int pageSize) {
		return departmentService.getAllDepartmentsWithPagination(pageNumber, pageSize);
	}

	@Operation(summary = "Get department by ID")
	@GetMapping("/{id}")
	public DepartmentResponseDto getDepartmentById(@PathVariable Long id) {
		return departmentService.getDepartmentById(id);
	}

	@Operation(summary = "Update department with employees")
	@PutMapping("/{id}")
	public DepartmentResponseDto updateDepartment(@PathVariable Long id,
			@Valid @RequestBody DepartmentRequestDto requestDto) {
		return departmentService.updateDepartment(id, requestDto);
	}

	@Operation(summary = "Delete department")
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteDepartment(@PathVariable Long id) {
		departmentService.deleteDepartment(id);
	}
}
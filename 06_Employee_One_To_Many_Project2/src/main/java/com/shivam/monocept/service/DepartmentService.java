package com.shivam.monocept.service;

import java.util.List;

import com.shivam.monocept.dto.DepartmentRequestDto;
import com.shivam.monocept.dto.DepartmentResponseDto;
import com.shivam.monocept.dto.PageResponseDto;

public interface DepartmentService {
	DepartmentResponseDto createDepartment(DepartmentRequestDto departmentRequestDto);

	List<DepartmentResponseDto> getAllDepartments();

	PageResponseDto<DepartmentResponseDto> getAllDepartmentsWithPagination(int pageNumber, int pageSize);

	DepartmentResponseDto getDepartmentById(Long id);

	DepartmentResponseDto updateDepartment(Long id, DepartmentRequestDto departmentRequestDto);

	void deleteDepartment(Long id);
}

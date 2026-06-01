package com.swabhav.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swabhav.demo.dto.DepartmentRequestDto;
import com.swabhav.demo.dto.DepartmentResponseDto;
import com.swabhav.demo.dto.EmployeeRequestDto;
import com.swabhav.demo.dto.PageResponseDto;
import com.swabhav.demo.exception.DuplicateResourceException;
import com.swabhav.demo.exception.ResourceNotFoundException;
import com.swabhav.demo.model.Department;
import com.swabhav.demo.model.Employee;
import com.swabhav.demo.repository.DepartmentRepository;
import com.swabhav.demo.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

	private final DepartmentRepository departmentRepository;
	private final EmployeeRepository employeeRepository;
	private final ModelMapper modelMapper;
	private static final Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);

	@Override
	@Transactional
	public DepartmentResponseDto createDepartment(DepartmentRequestDto requestDto) {

		logger.info("Creating department with name: {}", requestDto.getDepartmentName());

		if (departmentRepository.existsByDepartmentName(requestDto.getDepartmentName())) {

			logger.error("Department already exists: {}", requestDto.getDepartmentName());

			throw new DuplicateResourceException("Department name already exists: " + requestDto.getDepartmentName());
		}

		validateEmployeeEmailsForCreate(requestDto.getEmployees());

		try {

			Department department = modelMapper.map(requestDto, Department.class);

			attachEmployeesToDepartment(department, requestDto.getEmployees());

			Department savedDepartment = departmentRepository.save(department);

			logger.info("Department created successfully with id: {}", savedDepartment.getId());

			return modelMapper.map(savedDepartment, DepartmentResponseDto.class);

		} catch (DataIntegrityViolationException ex) {

			logger.error("Duplicate data found while creating department");

			throw new DuplicateResourceException(
					"Duplicate data found. Please check department name or employee email.");
		}
	} // Rest of the methods remain same (getAll, pagination, update, delete, etc.)

	@Override
	public List<DepartmentResponseDto> getAllDepartments() {
		List<Department> departments = departmentRepository.findAll();
		return departments.stream().map(dept -> modelMapper.map(dept, DepartmentResponseDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public PageResponseDto<DepartmentResponseDto> getAllDepartmentsWithPagination(int pageNumber, int pageSize) {
		validatePagination(pageNumber, pageSize);
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Department> departmentPage = departmentRepository.findAll(pageable);

		List<DepartmentResponseDto> content = departmentPage.getContent().stream()
				.map(dept -> modelMapper.map(dept, DepartmentResponseDto.class)).collect(Collectors.toList());

		PageResponseDto<DepartmentResponseDto> pageResponse = new PageResponseDto<>();
		pageResponse.setContent(content);
		pageResponse.setPageNumber(departmentPage.getNumber());
		pageResponse.setPageSize(departmentPage.getSize());
		pageResponse.setTotalElements(departmentPage.getTotalElements());
		pageResponse.setTotalPages(departmentPage.getTotalPages());
		pageResponse.setLastPage(departmentPage.isLast());

		return pageResponse;
	}

	@Override
	public DepartmentResponseDto getDepartmentById(Long id) {

		logger.info("Fetching department with id: {}", id);

		Department department = findDepartmentById(id);

		return modelMapper.map(department, DepartmentResponseDto.class);
	}

	@Override
	@Transactional
	public DepartmentResponseDto updateDepartment(Long id, DepartmentRequestDto requestDto) {

		logger.info("Updating department with id: {}", id);

		Department existing = findDepartmentById(id);

		if (departmentRepository.existsByDepartmentNameAndIdNot(requestDto.getDepartmentName(), id)) {

			logger.error("Duplicate department name found: {}", requestDto.getDepartmentName());

			throw new DuplicateResourceException("Department name already exists: " + requestDto.getDepartmentName());
		}

		existing.setDepartmentName(requestDto.getDepartmentName());
		existing.setLocation(requestDto.getLocation());

		existing.getEmployees().clear();

		attachEmployeesToDepartment(existing, requestDto.getEmployees());

		Department updated = departmentRepository.save(existing);

		logger.info("Department updated successfully with id: {}", id);

		return modelMapper.map(updated, DepartmentResponseDto.class);
	}

	@Override
	@Transactional
	public void deleteDepartment(Long id) {

		logger.info("Deleting department with id: {}", id);

		Department department = findDepartmentById(id);

		departmentRepository.delete(department);

		logger.info("Department deleted successfully with id: {}", id);
	}
	// ==================== Private Helpers ====================

	private Department findDepartmentById(Long id) {

		return departmentRepository.findById(id).orElseThrow(() -> {

			logger.error("Department not found with id: {}", id);

			return new ResourceNotFoundException("Department not found with id: " + id);
		});
	}

	private void attachEmployeesToDepartment(Department department, List<EmployeeRequestDto> dtos) {
		for (EmployeeRequestDto dto : dtos) {
			Employee employee = modelMapper.map(dto, Employee.class);
			department.addEmployee(employee);
		}
	}

	private void validateEmployeeEmailsForCreate(List<EmployeeRequestDto> employees) {

		for (EmployeeRequestDto emp : employees) {

			if (employeeRepository.existsByEmail(emp.getEmail())) {

				logger.error("Duplicate employee email found: {}", emp.getEmail());

				throw new DuplicateResourceException("Employee email already exists: " + emp.getEmail());
			}
		}
	}

	private void validateEmployeeEmailsForUpdate(List<EmployeeRequestDto> employees, Long departmentId) {
		for (EmployeeRequestDto emp : employees) {
			if (employeeRepository.existsByEmail(emp.getEmail())) {
				throw new DuplicateResourceException("Employee email already exists: " + emp.getEmail());
			}
		}
	}

	private void validatePagination(int pageNumber, int pageSize) {
		if (pageNumber < 0) {
			throw new IllegalArgumentException("Page number must not be negative");
		}
		if (pageSize <= 0 || pageSize > 100) {
			throw new IllegalArgumentException("Page size must be between 1 and 100");
		}
	}
}
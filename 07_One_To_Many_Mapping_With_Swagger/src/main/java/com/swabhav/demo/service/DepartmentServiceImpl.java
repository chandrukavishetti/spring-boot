package com.swabhav.demo.service;

import com.swabhav.demo.dto.DepartmentRequestDto;
import com.swabhav.demo.dto.DepartmentResponseDto;
import com.swabhav.demo.dto.EmployeeRequestDto;
import com.swabhav.demo.dto.EmployeeResponseDto;
import com.swabhav.demo.dto.PageResponseDto;
import com.swabhav.demo.exception.DuplicateResourceException;
import com.swabhav.demo.exception.ResourceNotFoundException;
import com.swabhav.demo.model.Department;
import com.swabhav.demo.model.Employee;
import com.swabhav.demo.repository.DepartmentRepository;
import com.swabhav.demo.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

	private final DepartmentRepository departmentRepository;
	private final EmployeeRepository employeeRepository;

	@Override
	@Transactional
	public DepartmentResponseDto createDepartment(DepartmentRequestDto requestDto) {

		if (departmentRepository.existsByDepartmentName(requestDto.getDepartmentName())) {
			throw new DuplicateResourceException("Department name already exists: " + requestDto.getDepartmentName());
		}

		validateDuplicateEmailsInsideRequest(requestDto.getEmployees());
		validateEmployeeEmailsForCreate(requestDto.getEmployees());

		try {
			Department department = new Department();
			department.setDepartmentName(requestDto.getDepartmentName().trim());
			department.setLocation(requestDto.getLocation().trim());

			attachEmployeesToDepartment(department, requestDto.getEmployees());

			Department savedDepartment = departmentRepository.save(department);
			return mapToDepartmentResponseDto(savedDepartment);

		} catch (DataIntegrityViolationException ex) {
			throw new DuplicateResourceException("Duplicate department name or employee email already exists.");
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<DepartmentResponseDto> getAllDepartments() {
		List<Department> departments = departmentRepository.findAll();

		return departments.stream()
				.map(this::mapToDepartmentResponseDto)
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public PageResponseDto<DepartmentResponseDto> getAllDepartmentsWithPagination(int pageNumber, int pageSize) {

		validatePagination(pageNumber, pageSize);

		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Department> departmentPage = departmentRepository.findAll(pageable);

		List<DepartmentResponseDto> content = departmentPage.getContent()
				.stream()
				.map(this::mapToDepartmentResponseDto)
				.collect(Collectors.toList());

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
	@Transactional(readOnly = true)
	public DepartmentResponseDto getDepartmentById(Long id) {
		Department department = findDepartmentById(id);
		return mapToDepartmentResponseDto(department);
	}

	@Override
	@Transactional
	public DepartmentResponseDto updateDepartment(Long id, DepartmentRequestDto requestDto) {

		Department existingDepartment = findDepartmentById(id);

		if (departmentRepository.existsByDepartmentNameAndIdNot(requestDto.getDepartmentName(), id)) {
			throw new DuplicateResourceException("Department name already exists: " + requestDto.getDepartmentName());
		}

		validateDuplicateEmailsInsideRequest(requestDto.getEmployees());
		validateEmployeeEmailsForUpdate(requestDto.getEmployees(), id);

		try {
			existingDepartment.setDepartmentName(requestDto.getDepartmentName().trim());
			existingDepartment.setLocation(requestDto.getLocation().trim());

			updateEmployees(existingDepartment, requestDto.getEmployees());

			Department updatedDepartment = departmentRepository.save(existingDepartment);
			return mapToDepartmentResponseDto(updatedDepartment);

		} catch (DataIntegrityViolationException ex) {
			throw new DuplicateResourceException("Duplicate department name or employee email already exists.");
		}
	}

	@Override
	@Transactional
	public void deleteDepartment(Long id) {
		Department department = findDepartmentById(id);
		departmentRepository.delete(department);
	}

	private Department findDepartmentById(Long id) {
		return departmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));
	}

	private void attachEmployeesToDepartment(Department department, List<EmployeeRequestDto> employeeDtos) {

		for (EmployeeRequestDto dto : employeeDtos) {
			Employee employee = new Employee();
			employee.setEmployeeName(dto.getEmployeeName().trim());
			employee.setEmail(dto.getEmail().trim());
			employee.setSalary(dto.getSalary());

			department.addEmployee(employee);
		}
	}

	private void updateEmployees(Department department, List<EmployeeRequestDto> employeeDtos) {

		Set<String> requestEmails = employeeDtos.stream()
				.map(dto -> normalizeEmail(dto.getEmail()))
				.collect(Collectors.toSet());

		List<Employee> employeesToRemove = department.getEmployees()
				.stream()
				.filter(employee -> !requestEmails.contains(normalizeEmail(employee.getEmail())))
				.collect(Collectors.toList());

		for (Employee employee : employeesToRemove) {
			department.removeEmployee(employee);
		}

		Map<String, Employee> existingEmployeesByEmail = department.getEmployees()
				.stream()
				.collect(Collectors.toMap(
						employee -> normalizeEmail(employee.getEmail()),
						Function.identity()
				));

		for (EmployeeRequestDto dto : employeeDtos) {

			String emailKey = normalizeEmail(dto.getEmail());
			Employee employee = existingEmployeesByEmail.get(emailKey);

			if (employee == null) {
				employee = new Employee();
				employee.setEmail(dto.getEmail().trim());
				employee.setEmployeeName(dto.getEmployeeName().trim());
				employee.setSalary(dto.getSalary());

				department.addEmployee(employee);
			} else {
				employee.setEmployeeName(dto.getEmployeeName().trim());
				employee.setSalary(dto.getSalary());
			}
		}
	}

	private void validateDuplicateEmailsInsideRequest(List<EmployeeRequestDto> employees) {

		Set<String> emails = new HashSet<>();

		for (EmployeeRequestDto employee : employees) {
			String email = normalizeEmail(employee.getEmail());

			if (!emails.add(email)) {
				throw new DuplicateResourceException("Duplicate employee email in request: " + employee.getEmail());
			}
		}
	}

	private void validateEmployeeEmailsForCreate(List<EmployeeRequestDto> employees) {

		for (EmployeeRequestDto employee : employees) {
			if (employeeRepository.existsByEmailIgnoreCase(employee.getEmail())) {
				throw new DuplicateResourceException("Employee email already exists: " + employee.getEmail());
			}
		}
	}

	private void validateEmployeeEmailsForUpdate(List<EmployeeRequestDto> employees, Long departmentId) {

		for (EmployeeRequestDto employee : employees) {
			if (employeeRepository.existsByEmailIgnoreCaseAndDepartment_IdNot(employee.getEmail(), departmentId)) {
				throw new DuplicateResourceException("Employee email already exists in another department: " + employee.getEmail());
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

	private String normalizeEmail(String email) {
		return email == null ? null : email.trim().toLowerCase();
	}

	private DepartmentResponseDto mapToDepartmentResponseDto(Department department) {

		DepartmentResponseDto responseDto = new DepartmentResponseDto();
		responseDto.setId(department.getId());
		responseDto.setDepartmentName(department.getDepartmentName());
		responseDto.setLocation(department.getLocation());

		List<EmployeeResponseDto> employees = department.getEmployees()
				.stream()
				.map(this::mapToEmployeeResponseDto)
				.collect(Collectors.toList());

		responseDto.setEmployees(employees);

		return responseDto;
	}

	private EmployeeResponseDto mapToEmployeeResponseDto(Employee employee) {

		EmployeeResponseDto responseDto = new EmployeeResponseDto();
		responseDto.setId(employee.getId());
		responseDto.setEmployeeName(employee.getEmployeeName());
		responseDto.setEmail(employee.getEmail());
		responseDto.setSalary(employee.getSalary());

		return responseDto;
	}
}

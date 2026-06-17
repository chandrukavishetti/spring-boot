package com.shivam.monocept.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.shivam.monocept.dto.DepartmentRequestDto;
import com.shivam.monocept.dto.DepartmentResponseDto;
import com.shivam.monocept.dto.EmployeeRequestDto;
import com.shivam.monocept.dto.PageResponseDto;
import com.shivam.monocept.entity.Department;
import com.shivam.monocept.entity.Employee;
import com.shivam.monocept.exception.DuplicateResourceException;
import com.shivam.monocept.exception.ResourceNotFoundException;
import com.shivam.monocept.repository.DepartmentRepository;
import com.shivam.monocept.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class DepartmentServiceImplementation implements DepartmentService {

	private final DepartmentRepository departmentRepository;
	private final EmployeeRepository employeeRepository;
	private final ModelMapper modelMapper;

	@Override
	public DepartmentResponseDto createDepartment(DepartmentRequestDto dto) {

		log.info("Creating department: {}", dto.getDepartmentName());

		if (departmentRepository.existsByDepartmentName(dto.getDepartmentName())) {

			log.warn("Duplicate department name: {}", dto.getDepartmentName());

			throw new DuplicateResourceException("Department name already exists");
		}

		validateEmployeeEmailsForCreate(dto.getEmployees());

		try {

			Department department = modelMapper.map(dto, Department.class);

			attachEmployeesToDepartment(department);

			Department savedDepartment = departmentRepository.save(department);

			log.info("Department created successfully with id: {}", savedDepartment.getId());

			return modelMapper.map(savedDepartment, DepartmentResponseDto.class);

		} catch (Exception e) {

	System.out.println("occoured exception"+e);
	
	
		}
		return null;
	}

	@Override
	public List<DepartmentResponseDto> getAllDepartments() {

		log.info("Fetching all departments");

		return departmentRepository.findAll().stream()
				.map(department -> modelMapper.map(department, DepartmentResponseDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public PageResponseDto<DepartmentResponseDto> getAllDepartmentsWithPagination(int pageNumber, int pageSize) {

		log.info("Fetching departments with pagination. Page: {}, Size: {}", pageNumber, pageSize);

		validatePagination(pageNumber, pageSize);

		PageRequest pageable = PageRequest.of(pageNumber, pageSize);

		Page<Department> page = departmentRepository.findAll(pageable);

		List<DepartmentResponseDto> content = page.getContent().stream()
				.map(department -> modelMapper.map(department, DepartmentResponseDto.class))
				.collect(Collectors.toList());

		return new PageResponseDto<>(content, page.getNumber(), page.getSize(), page.getTotalElements(),
				page.getTotalPages(), page.isLast());
	}

	@Override
	public DepartmentResponseDto getDepartmentById(Long id) {

		log.info("Fetching department with id: {}", id);

		Department department = findDepartmentById(id);

		return modelMapper.map(department, DepartmentResponseDto.class);
	}

	@Override
	public DepartmentResponseDto updateDepartment(Long id, DepartmentRequestDto dto) {

	    log.info("Updating department with id: {}", id);

	    Department department = findDepartmentById(id);

	    if (departmentRepository.existsByDepartmentNameAndIdNot(
	            dto.getDepartmentName(), id)) {

	        throw new DuplicateResourceException(
	                "Department name already exists");
	    }

	    department.setDepartmentName(dto.getDepartmentName());
	    department.setLocation(dto.getLocation());

	    // Clear existing employees
	    department.getEmployees().clear();

	    // Add new employees to same collection
	    for (EmployeeRequestDto employeeDto : dto.getEmployees()) {

	        Employee employee =
	                modelMapper.map(employeeDto, Employee.class);

	        employee.setDepartment(department);

	        department.getEmployees().add(employee);
	    }

	    Department updatedDepartment =
	            departmentRepository.save(department);

	    log.info("Department updated successfully with id: {}", id);

	    return modelMapper.map(
	            updatedDepartment,
	            DepartmentResponseDto.class);
	}
	@Override
	public void deleteDepartment(Long id) {

		log.info("Deleting department with id: {}", id);

		Department department = findDepartmentById(id);

		departmentRepository.delete(department);

		log.info("Department deleted successfully with id: {}", id);
	}

	

	private Department findDepartmentById(Long id) {

		log.info("Searching department with id: {}", id);

		return departmentRepository.findById(id).orElseThrow(() -> {

			log.error("Department not found with id: {}", id);

			return new ResourceNotFoundException("Department not found with id: " + id);
		});
	}

	private void attachEmployeesToDepartment(Department department) {

		department.getEmployees().forEach(employee -> employee.setDepartment(department));
	}

	private void validateEmployeeEmailsForCreate(List<EmployeeRequestDto> employees) {

		for (EmployeeRequestDto employee : employees) {

			if (employeeRepository.existsByEmail(employee.getEmail())) {

				log.warn("Duplicate employee email: {}", employee.getEmail());

				throw new DuplicateResourceException("Employee email already exists: " + employee.getEmail());
			}
		}
	}

	private void validateEmployeeEmailsForUpdate(List<EmployeeRequestDto> employees) {

		for (EmployeeRequestDto employee : employees) {

			if (employeeRepository.existsByEmail(employee.getEmail())) {

				log.warn("Duplicate employee email during update: {}", employee.getEmail());

				throw new DuplicateResourceException("Employee email already exists: " + employee.getEmail());
			}
		}
	}

	private void validatePagination(int pageNumber, int pageSize) {

		if (pageNumber < 0) {

			log.warn("Invalid page number: {}", pageNumber);

			throw new IllegalArgumentException("Page number cannot be negative");
		}

		if (pageSize <= 0 || pageSize > 100) {

			log.warn("Invalid page size: {}", pageSize);

			throw new IllegalArgumentException("Page size must be between 1 and 100");
		}
	}
}
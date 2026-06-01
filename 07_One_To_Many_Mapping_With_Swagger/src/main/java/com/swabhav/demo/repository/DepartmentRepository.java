package com.swabhav.demo.repository;

import com.swabhav.demo.model.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

	boolean existsByDepartmentName(String departmentName);

	boolean existsByDepartmentNameAndIdNot(String departmentName, Long id);

	// Optional: You can add this if needed for find by name (not mandatory as per
	// SRS)
	Optional<Department> findByDepartmentName(String departmentName);
}
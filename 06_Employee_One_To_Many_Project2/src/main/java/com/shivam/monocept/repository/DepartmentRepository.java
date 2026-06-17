package com.shivam.monocept.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shivam.monocept.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
	boolean existsByDepartmentName(String departmentName); 
	boolean existsByDepartmentNameAndIdNot(String departmentName,long id);
}

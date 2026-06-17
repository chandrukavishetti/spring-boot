package com.shivam.monocept.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shivam.monocept.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	boolean existsByEmail(String Email);
	boolean existsByEmailAndIdNot(String Email,long id);
}

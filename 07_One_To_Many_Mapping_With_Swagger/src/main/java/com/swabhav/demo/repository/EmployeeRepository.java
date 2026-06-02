package com.swabhav.demo.repository;

import com.swabhav.demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	boolean existsByEmailIgnoreCase(String email);

	boolean existsByEmailIgnoreCaseAndDepartment_IdNot(String email, Long departmentId);
}

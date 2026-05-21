package com.chandru.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	List<Employee> findByEmpName(String empName);

	List<Employee> findByCityCode(int cityCode);

}
package com.chandru.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chandru.demo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}

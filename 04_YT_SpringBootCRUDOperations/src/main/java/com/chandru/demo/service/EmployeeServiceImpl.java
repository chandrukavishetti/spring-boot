package com.chandru.demo.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.chandru.demo.entity.Employee;
import com.chandru.demo.repository.EmployeeRepository;

public class EmployeeServiceImpl implements EmployeeService {


	@Autowired
	EmployeeRepository repo;

	public Employee createEmp(Employee emp) {
		return repo.save(emp);

	}

}

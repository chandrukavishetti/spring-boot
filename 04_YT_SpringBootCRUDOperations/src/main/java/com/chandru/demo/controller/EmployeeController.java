package com.chandru.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chandru.demo.entity.Employee;
import com.chandru.demo.service.EmployeeService;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

	@Autowired
	EmployeeService service;
	
	@PostMapping("/save")
	public Employee saveEmployee(@RequestBody Employee emp) {
		return service.createEmp(emp);
	}

}

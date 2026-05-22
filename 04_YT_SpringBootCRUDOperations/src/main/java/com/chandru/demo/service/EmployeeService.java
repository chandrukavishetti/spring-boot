package com.chandru.demo.service;

import org.springframework.stereotype.Service;

import com.chandru.demo.entity.Employee;

@Service
public interface EmployeeService {
	
	//create,getemp,getempbyid,delete,update
	public Employee createEmp(Employee emp);

}

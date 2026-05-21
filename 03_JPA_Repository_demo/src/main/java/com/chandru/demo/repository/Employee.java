package com.chandru.demo.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Employee {

	@Id
	private int empId;

	@Column(name = "emp_name")
	private String empName;

	@Column(name = "city_code")
	private int cityCode;

	@Column(name = "emp_age")
	private int empAge;

	@Column(name = "emp_salary")
	private float empSalary;

	@Column(name = "city_name")
	private String cityName;

	public Employee() {
		super();
	}

	public int getEmp_id() {
		return getEmp_id();
	}

	public void setEmp_id(int emp_id) {
		this.empId = emp_id;
	}

	public String getEmp_name() {
		return empName;
	}

	public void setEmp_name(String emp_name) {
		this.empName = emp_name;
	}

	public int getCity_code() {
		return cityCode;
	}

	public void setCity_code(int city_code) {
		this.cityCode = city_code;
	}

	public int getEmp_age() {
		return empAge;
	}

	public void setEmp_age(int emp_age) {
		this.empAge = emp_age;
	}

	public float getEmp_salary() {
		return empSalary;
	}

	public void setEmp_salary(float emp_salary) {
		this.empSalary = emp_salary;
	}

	public String getCity_name() {
		return cityName;
	}

	public void setCity_name(String city_name) {
		this.cityName = city_name;
	}

	@Override
	public String toString() {
		return "Employee [emp_id=" + empId + ", emp_name=" + empName + ", city_code=" + cityCode + ", emp_age=" + empAge
				+ ", emp_salary=" + empSalary + ", city_name=" + cityName + "]";
	}

	public Employee(int emp_id, String emp_name, int city_code, int emp_age, float emp_salary, String city_name) {
		super();
		this.empId = emp_id;
		this.empName = emp_name;
		this.cityCode = city_code;
		this.empAge = emp_age;
		this.empSalary = emp_salary;
		this.cityName = city_name;
	}

}

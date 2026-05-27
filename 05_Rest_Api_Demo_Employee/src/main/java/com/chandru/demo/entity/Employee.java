package com.chandru.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "employees")
public class Employee {

	@Id
	@Column
	@JsonProperty("emp_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employeeId;

	@Column
	@JsonProperty("emp_name")
	@NotBlank(message = "name cannot be blank")
	private String employeeName;

	@Column
	@JsonProperty("emp_email")
	@NotBlank(message = "emil cannot be blank ")
	@Email(message = "please provide a valid email address")
	private String employeeEmail;

	@Column
	private long employeePhone;

	private String employeeGender;

	private float employeeSalary;

	private String employeeRole;

	private int employeeExperience;

}

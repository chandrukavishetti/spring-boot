package com.chandru.demo.entity;

import com.chandru.demo.enums.Gender;
import com.chandru.demo.enums.Role;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "employees")
public class Employee {

	@Id
	@Column(name = "emp_id")
	@JsonProperty("emp_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employeeId;

	@Column(name = "emp_name")
	@JsonProperty("emp_name")
	@NotBlank(message = "name cannot be blank")
	private String employeeName;

	@Column(name = "emp_email")
	@JsonProperty("emp_email")
	@NotBlank(message = "emil cannot be blank ")
	@Email(message = "please provide a valid email address")
	private String employeeEmail;

	@Column(name = "emp_phone")
	@JsonProperty("emp_phone")
	@NotBlank(message = "phone number cannot be blank")
	private String employeePhone;

	@Column(name = "emp_gender")
	@JsonProperty("emp_gender")
	@NotNull(message = "gender cannot be null")
	private Gender employeeGender;

	@Column(name = "emp_salary")
	@JsonProperty("emp_salary")
	private float employeeSalary;

	@Column(name = "emp_role")
	@JsonProperty("emp_role")
	@NotNull(message = "employee role cannot be null")
	private Role employeeRole;

	@Column(name = "emp_experience")
	@JsonProperty("emp_experience")
	private int employeeExperience;

}

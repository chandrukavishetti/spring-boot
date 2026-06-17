package com.chandru.demo.dto;

import com.chandru.demo.enums.Gender;
import com.chandru.demo.enums.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequestDTO {

	@NotBlank(message = "name cannot be blank")
	private String employeeName;

	@Email(message = "enter the valid email")
	private String employeeEmail;

	@NotBlank(message = "phone number cannot be blank")
	private String employeePhone;

	@NotNull(message = "gender is required")
	private Gender employeeGender;

	@NotBlank(message = "salary cannot be blank")
	private float employeeSalary;

	@NotNull(message = "role is required")
	private Role employeeRole;

	@NotBlank(message = "experies is cannot be blank")
	private int employeeExperience;
}

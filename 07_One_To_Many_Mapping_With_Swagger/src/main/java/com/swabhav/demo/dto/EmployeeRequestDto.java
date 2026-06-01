package com.swabhav.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequestDto {

	@JsonProperty("employee_name")
	@NotBlank(message = "Employee name is required")
	private String employeeName;

	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	private String email;

	@Positive(message = "Salary must be greater than zero")
	private Double salary;
}
package com.swabhav.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentRequestDto {

	@JsonProperty("department_name")
	@NotBlank(message = "Department name is required")
	private String departmentName;

	@NotBlank(message = "Location is required")
	private String location;

	@NotEmpty(message = "At least one employee is required")
	@Valid
	private List<EmployeeRequestDto> employees;
}
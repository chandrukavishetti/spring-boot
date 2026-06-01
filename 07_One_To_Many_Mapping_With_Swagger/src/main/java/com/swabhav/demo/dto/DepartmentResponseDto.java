package com.swabhav.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentResponseDto {

	private Long id;

	@JsonProperty("department_name")
	private String departmentName;

	private String location;

	private List<EmployeeResponseDto> employees;
}
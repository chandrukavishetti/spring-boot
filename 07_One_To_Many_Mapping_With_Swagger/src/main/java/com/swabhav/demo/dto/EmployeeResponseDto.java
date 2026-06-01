package com.swabhav.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponseDto {

	private Long id;

	@JsonProperty("employee_name")
	private String employeeName;

	private String email;

	private Double salary;
}
package com.chandru.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponseDTO {

	
	public String employeeName;
	
	public String employeeEmail;
	
	public String employeePhone;
	
	public String employeeGender;
	
	public String employeeExperience;
}

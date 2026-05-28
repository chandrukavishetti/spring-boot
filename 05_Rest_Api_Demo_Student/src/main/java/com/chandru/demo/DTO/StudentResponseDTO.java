package com.chandru.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponseDTO {

	private String sName;

	private String sDepartment;

//	public StudentResponseDTO() {
//	}
//
//	public StudentResponseDTO(String sName, String sDepartment) {
//		this.sName = sName;
//		this.sDepartment = sDepartment;
//	}

//	public String getS_name() {
//		return s_name;
//	}
//
//	public void setS_name(String s_name) {
//		this.s_name = s_name;
//	}
//
//	public String getS_department() {
//		return s_department;
//	}
//
//	public void setS_department(String s_department) {
//		this.s_department = s_department;
//	}
//
//	@Override
//	public String toString() {
//		return "StudentResponseDTO [s_name=" + s_name + ", s_department=" + s_department + "]";
//	}
}
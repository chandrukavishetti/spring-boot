package com.chandru.demo.DTO;

public class StudentResponseDTO {

	private String s_name;

	private String s_department;

	public StudentResponseDTO() {
	}

	public StudentResponseDTO(String s_name, String s_department) {
		this.s_name = s_name;
		this.s_department = s_department;
	}

	public String getS_name() {
		return s_name;
	}

	public void setS_name(String s_name) {
		this.s_name = s_name;
	}

	public String getS_department() {
		return s_department;
	}

	public void setS_department(String s_department) {
		this.s_department = s_department;
	}

	@Override
	public String toString() {
		return "StudentResponseDTO [s_name=" + s_name + ", s_department=" + s_department + "]";
	}
}
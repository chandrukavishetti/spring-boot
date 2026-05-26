package com.chandru.demo.DTO;

public class StudentRequestDTO {

	private String s_name;

	private Integer s_age;

	private String s_department;

	public StudentRequestDTO() {
	}

	public StudentRequestDTO(String s_name, Integer s_age, String s_department) {
		this.s_name = s_name;
		this.s_age = s_age;
		this.s_department = s_department;
	}

	public String getS_name() {
		return s_name;
	}

	public void setS_name(String s_name) {
		this.s_name = s_name;
	}

	public Integer getS_age() {
		return s_age;
	}

	public void setS_age(Integer s_age) {
		this.s_age = s_age;
	}

	public String getS_department() {
		return s_department;
	}

	public void setS_department(String s_department) {
		this.s_department = s_department;
	}

	@Override
	public String toString() {
		return "StudentRequestDTO [s_name=" + s_name + ", s_age=" + s_age + ", s_department=" + s_department + "]";
	}
}
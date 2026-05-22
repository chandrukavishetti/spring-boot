package com.chandru.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "students")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "std_id")
	private int s_id;

	@JsonProperty("s_name") // it is used to simplyfies the name of the properties when we are checking in
							// postman intead of giving the entire table just give the like s_name
	@Column(name = "std_name")
	@NotBlank(message = "Name cannot be empty")
	private String s_name;

	@JsonProperty("s_age")
	@Column(name = "std_age")
	@NotNull(message = "Age cannot be null")
	@Min(value = 18, message = "Age should be greater than or equal to 18")
	private Integer s_age;

	@JsonProperty("s_dept")
	@Column(name = "std_department")
	@NotBlank(message = "Department cannot be empty")
	private String s_department;

	// Default Constructor
	public Student() {
	}

	// Parameterized Constructor
	public Student(String s_name, Integer s_age, String s_department) {
		this.s_name = s_name;
		this.s_age = s_age;
		this.s_department = s_department;
	}

	// Getters and Setters
	public int getS_id() {
		return s_id;
	}

	public void setS_id(int s_id) {
		this.s_id = s_id;
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
		return "Student [s_id=" + s_id + ", s_name=" + s_name + ", s_age=" + s_age + ", s_department=" + s_department
				+ "]";
	}
}
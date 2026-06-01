package com.swabhav.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StudentRequestDto {

	@JsonProperty("full_name")
	@NotBlank(message = "name cannot be blank")
	private String fullName;

	@NotNull(message = "age cannot be null")
	@Min(1)
	private Integer age;

	@Valid
	@NotNull(message = "profile cannot be null")
	private StudentProfileRequestDto profile;

}

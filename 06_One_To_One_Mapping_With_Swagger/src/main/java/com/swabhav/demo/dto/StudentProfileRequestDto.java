package com.swabhav.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StudentProfileRequestDto {

	@Email
	@NotBlank(message = "email cannot be blank")
	private String email;

	@NotBlank(message = "phone number cannot be blank")
	private String phone;

	@NotBlank(message = "city name cannot be blank")
	private String city;
}

package com.swabhav.demo.dto;

import lombok.Data;

@Data
public class StudentProfileResponseDto {

	private Long id;

	private String email;

	private String phone;

	private String city;
}

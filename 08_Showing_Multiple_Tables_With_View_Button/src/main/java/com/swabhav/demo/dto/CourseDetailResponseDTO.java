package com.swabhav.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDetailResponseDTO {

	private Long id;

	private String courseName;

	private String duration;

	private Double price;
}
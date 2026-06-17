package com.swabhav.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swabhav.demo.dto.CourseDetailResponseDTO;
import com.swabhav.demo.dto.CourseResponseDTO;
import com.swabhav.demo.dto.StudentResponseDTO;
import com.swabhav.demo.service.StudentService;

@RestController
@RequestMapping("/api")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@GetMapping("/students")
	public List<StudentResponseDTO> getAllStudents() {
		return studentService.getAllStudents();
	}

	@GetMapping("/students/{studentId}/courses")
	public List<CourseResponseDTO> getCoursesByStudentId(@PathVariable Long studentId) {

		return studentService.getCoursesByStudentId(studentId);
	}

	@GetMapping("/courses/{courseId}/details")
	public CourseDetailResponseDTO getCourseDetailByCourseId(@PathVariable Long courseId) {

		return studentService.getCourseDetailByCourseId(courseId);
	}
}
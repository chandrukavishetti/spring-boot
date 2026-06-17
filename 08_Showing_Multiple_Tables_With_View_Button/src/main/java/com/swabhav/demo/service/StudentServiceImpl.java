package com.swabhav.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swabhav.demo.dto.CourseDetailResponseDTO;
import com.swabhav.demo.dto.CourseResponseDTO;
import com.swabhav.demo.dto.StudentResponseDTO;
import com.swabhav.demo.model.Course;
import com.swabhav.demo.model.CourseDetail;
import com.swabhav.demo.model.Student;
import com.swabhav.demo.repository.CourseDetailRepository;
import com.swabhav.demo.repository.CourseRepository;
import com.swabhav.demo.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private CourseDetailRepository courseDetailRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<StudentResponseDTO> getAllStudents() {

		List<Student> students = studentRepository.findAll();

		return students.stream().map(student -> modelMapper.map(student, StudentResponseDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<CourseResponseDTO> getCoursesByStudentId(Long studentId) {

		List<Course> courses = courseRepository.findByStudentId(studentId);

		return courses.stream().map(course -> modelMapper.map(course, CourseResponseDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public CourseDetailResponseDTO getCourseDetailByCourseId(Long courseId) {

		CourseDetail courseDetail = courseDetailRepository.findByCourseId(courseId)
				.orElseThrow(() -> new RuntimeException("Course Detail Not Found"));

		CourseDetailResponseDTO dto = modelMapper.map(courseDetail, CourseDetailResponseDTO.class);

		dto.setCourseName(courseDetail.getCourse().getCourseName());

		return dto;
	}
}
package com.chandru.demo.service;

import java.util.List;
import java.util.Map;

import com.chandru.demo.DTO.StudentRequestDTO;
import com.chandru.demo.DTO.StudentResponseDTO;
import com.chandru.demo.entity.Student;

public interface StudentService {

	// Student createStudent(Student student);

	List<Student> createMultipleStudents(List<Student> students);

	Student getStudentById(int id);

	// List<Student> getAllStudents();

	Student updateStudentName(int id, Student student);

	Student updateStudentDept(int id, Student student);

	Student updateStudentDeptAndAge(int id, Student student);

	String deleteStudent(int id);

	Student updatePartially(int id, Map<String, Object> updatedData);

	StudentResponseDTO createStudent(StudentRequestDTO dto);

	List<StudentResponseDTO> getAllStudents();

}

package com.chandru.demo.service;

import java.util.List;
import java.util.Map;

import com.chandru.demo.DTO.PageResponseDTO;
import com.chandru.demo.DTO.StudentRequestDTO;
import com.chandru.demo.DTO.StudentResponseDTO;
import com.chandru.demo.DTO.UpdateStudentDeptDTO;
import com.chandru.demo.entity.Student;

public interface StudentService {

	StudentResponseDTO createStudent(StudentRequestDTO dto);

//	List<Student> createMultipleStudents(List<StudentRequestDTO> students);
	List<StudentResponseDTO> createMultipleStudents(List<StudentRequestDTO> studentRequestDtos);

	StudentResponseDTO getStudentById(int id);

	List<StudentResponseDTO> getAllStudents();

	StudentResponseDTO updateStudentName(int id, Student student);

	StudentResponseDTO updateStudentDept(int id, UpdateStudentDeptDTO dto);

	Student updateStudentDeptAndAge(int id, Student student);

	String deleteStudent(int id);

	Student updatePartially(int id, Map<String, Object> updatedData);

	PageResponseDTO<StudentResponseDTO> getAllStudentsWithPagination(int pageNumber, int pageSize);
}
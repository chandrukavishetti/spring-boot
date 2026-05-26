package com.chandru.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chandru.demo.DTO.StudentRequestDTO;
import com.chandru.demo.DTO.StudentResponseDTO;
import com.chandru.demo.Exception.StudentNotFoundException;
import com.chandru.demo.entity.Student;
import com.chandru.demo.repository.StudentRepository;

@Service
public class StudentServiceImplementation implements StudentService {

	private StudentRepository studentRepository;

	@Autowired
	public StudentServiceImplementation(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@Override
	public StudentResponseDTO createStudent(StudentRequestDTO dto) {

		Student student = convertToEntity(dto);

		Student savedStudent = studentRepository.save(student);

		return convertToDTO(savedStudent);
	}
//	@Override
//	public Student createStudent(Student student) {
//		return studentRepository.save(student);
//	}

	@Override
	public List<Student> createMultipleStudents(List<Student> students) {

		return studentRepository.saveAll(students);
	}

	@Override
	public Student getStudentById(int id) {

		return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
	}

	@Override
	public List<StudentResponseDTO> getAllStudents() {

		List<Student> students = studentRepository.findAll();

		return students.stream().map(this::convertToDTO).toList();
	}
//	@Override
//	public List<Student> getAllStudents() {
//		return studentRepository.findAll();
//	}

	@Override
	public Student updateStudentName(int id, Student student) {

		Student existingStudent = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));

		existingStudent.setS_name(student.getS_name());

		return studentRepository.save(existingStudent);
	}

	@Override
	public Student updateStudentDept(int id, Student student) {

		Student existingStudent = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));

		existingStudent.setS_department(student.getS_department());

		return studentRepository.save(existingStudent);
	}

	@Override
	public Student updateStudentDeptAndAge(int id, Student student) {

		Student existingStudent = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));

		existingStudent.setS_age(student.getS_age());
		existingStudent.setS_department(student.getS_department());

		return studentRepository.save(existingStudent);
	}

	@Override
	public String deleteStudent(int id) {

		Student existingStudent = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));

		studentRepository.delete(existingStudent);

		return "Student deleted successfully";
	}

	@Override
	public Student updatePartially(int id, Map<String, Object> updatedData) {

		Student existingStudent = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));

		if (updatedData.containsKey("s_name")) {
			existingStudent.setS_name((String) updatedData.get("s_name"));
		}

		if (updatedData.containsKey("s_age")) {
			existingStudent.setS_age((Integer) updatedData.get("s_age"));
		}

		if (updatedData.containsKey("s_dept")) {
			existingStudent.setS_department((String) updatedData.get("s_dept"));
		}

		return studentRepository.save(existingStudent);
	}

	private Student convertToEntity(StudentRequestDTO dto) {

		Student student = new Student();

		student.setS_name(dto.getS_name());
		student.setS_age(dto.getS_age());
		student.setS_department(dto.getS_department());

		return student;
	}

	private StudentResponseDTO convertToDTO(Student student) {

		StudentResponseDTO dto = new StudentResponseDTO();

		dto.setS_name(student.getS_name());
		dto.setS_department(student.getS_department());

		return dto;
	}
}
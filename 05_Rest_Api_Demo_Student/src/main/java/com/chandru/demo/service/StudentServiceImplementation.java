package com.chandru.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.chandru.demo.DTO.PageResponseDTO;
import com.chandru.demo.DTO.StudentRequestDTO;
import com.chandru.demo.DTO.StudentResponseDTO;
import com.chandru.demo.DTO.UpdateStudentDeptDTO;
import com.chandru.demo.Exception.StudentNotFoundException;
import com.chandru.demo.entity.Student;
import com.chandru.demo.repository.StudentRepository;

@Service
public class StudentServiceImplementation implements StudentService {

	private StudentRepository studentRepository;

	private ModelMapper modelMapper;

	@Autowired
	public StudentServiceImplementation(StudentRepository studentRepository, ModelMapper modelMapper) {
		this.studentRepository = studentRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public StudentResponseDTO createStudent(StudentRequestDTO studentRequestDto) {

		Student student = modelMapper.map(studentRequestDto, Student.class);// DTO -> Entity

		Student savedStudent = studentRepository.save(student);

		return modelMapper.map(savedStudent, StudentResponseDTO.class);// Entity -> DTO
	}

//		Student student = convertToEntity(studentRequestDto);
//
//		Student savedStudent = studentRepository.save(student);
//
//		return convertToDTO(savedStudent);
//	}

//	@Override
//	public Student createStudent(Student student) {
//		return studentRepository.save(student);
//	}
	@Override
	public List<StudentResponseDTO> createMultipleStudents(List<StudentRequestDTO> studentRequestDtos) {

		// DTO List -> Entity List
		List<Student> students = studentRequestDtos.stream().map(dto -> modelMapper.map(dto, Student.class)).toList();

		// Save all students
		List<Student> savedStudents = studentRepository.saveAll(students);

		// Entity List -> Response DTO List
		return savedStudents.stream().map(student -> modelMapper.map(student, StudentResponseDTO.class)).toList();
	}

	@Override
	public StudentResponseDTO getStudentById(int id) {

		Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));

		return modelMapper.map(student, StudentResponseDTO.class);
	}

	@Override
	public List<StudentResponseDTO> getAllStudents() {

		List<Student> students = studentRepository.findAll();

		return students.stream().map(student -> modelMapper.map(student, StudentResponseDTO.class)).toList();
	}
//	@Override
//	public List<Student> getAllStudents() {
//		return studentRepository.findAll();
//	}

	@Override
	public StudentResponseDTO updateStudentName(int id, Student student) {

		Student existingStudent = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));

		existingStudent.setSName(student.getSName());

		Student updatedStudent = studentRepository.save(existingStudent);

		return modelMapper.map(updatedStudent, StudentResponseDTO.class);
	}

	@Override
	public StudentResponseDTO updateStudentDept(int id, UpdateStudentDeptDTO dto) {

		Student existingStudent = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));

		existingStudent.setSDepartment(dto.getSDepartment());

		Student updatedStudent = studentRepository.save(existingStudent);

		return modelMapper.map(updatedStudent, StudentResponseDTO.class);
	}

	@Override
	public Student updateStudentDeptAndAge(int id, Student student) {

		Student existingStudent = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));

		existingStudent.setSAge(student.getSAge());
		existingStudent.setSDepartment(student.getSDepartment());

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
			existingStudent.setSName((String) updatedData.get("s_name"));
		}

		if (updatedData.containsKey("s_age")) {
			existingStudent.setSAge((Integer) updatedData.get("s_age"));
		}

		if (updatedData.containsKey("s_dept")) {
			existingStudent.setSDepartment((String) updatedData.get("s_dept"));
		}

		return studentRepository.save(existingStudent);
	}

	@Override
	public PageResponseDTO<StudentResponseDTO> getAllStudentsWithPagination(int pageNumber, int pageSize) {

		PageRequest pagable = PageRequest.of(pageNumber, pageSize);

		org.springframework.data.domain.Page<Student> studentPage = studentRepository.findAll(pagable);

		List<Student> students = studentPage.getContent();

		List<StudentResponseDTO> studentResponses = new ArrayList<>();

		for (Student s : students) {
			StudentResponseDTO responseDto = modelMapper.map(s, StudentResponseDTO.class);
			studentResponses.add(responseDto);
		}
		PageResponseDTO<StudentResponseDTO> pageResponseDto = new PageResponseDTO<>();

		pageResponseDto.setContent(studentResponses);
		pageResponseDto.setPageNumber(studentPage.getNumber());
		pageResponseDto.setPageSize(studentPage.getSize());
		pageResponseDto.setTotalCount(studentPage.getTotalElements());
		pageResponseDto.setLastPage(studentPage.isLast());
		pageResponseDto.setTotalPage(studentPage.getTotalPages());

		return pageResponseDto;

	}

//	private Student convertToEntity(StudentRequestDTO dto) {
//
//		Student student = new Student();
//
//		student.setS_name(dto.getS_name());
//		student.setS_age(dto.getS_age());
//		student.setS_department(dto.getS_department());
//
//		return student;
//	}
//
//	private StudentResponseDTO convertToDTO(Student student) {
//
//		StudentResponseDTO dto = new StudentResponseDTO();
//
//		dto.setS_name(student.getS_name());
//		dto.setS_department(student.getS_department());
//
//		return dto;
//	}
}
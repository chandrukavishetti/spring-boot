package com.swabhav.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.swabhav.demo.dto.PageResponseDto;
import com.swabhav.demo.dto.StudentProfileRequestDto;
import com.swabhav.demo.dto.StudentRequestDto;
import com.swabhav.demo.dto.StudentResponseDto;
import com.swabhav.demo.exception.DuplicateResourceException;
import com.swabhav.demo.exception.ResourceNotFoundException;
import com.swabhav.demo.model.Student;
import com.swabhav.demo.model.StudentProfile;
import com.swabhav.demo.repository.StudentProfileRepository;
import com.swabhav.demo.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

	private final StudentRepository studentRepository;
	private final StudentProfileRepository studentProfileRepository;
	private final ModelMapper modelMapper;

	@Override
	public StudentResponseDto createStudent(StudentRequestDto requestDto) {
		if (studentProfileRepository.existsByEmail(requestDto.getProfile().getEmail())) {
			throw new DuplicateResourceException("Email already exists: " + requestDto.getProfile().getEmail());
		}

		Student student = new Student();
		student.setFullName(requestDto.getFullName());
		student.setAge(requestDto.getAge());

		StudentProfile profile = modelMapper.map(requestDto.getProfile(), StudentProfile.class);
		student.setProfile(profile);
		profile.setStudent(student);

		Student savedStudent = studentRepository.save(student);
		return modelMapper.map(savedStudent, StudentResponseDto.class);
	}

	@Override
	public List<StudentResponseDto> getAllStudents() {
		return studentRepository.findAll().stream().map(student -> modelMapper.map(student, StudentResponseDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public PageResponseDto<StudentResponseDto> getAllStudentsWithPagination(int pageNumber, int pageSize) {
		validatePagination(pageNumber, pageSize);
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Student> studentPage = studentRepository.findAll(pageable);

		List<StudentResponseDto> content = studentPage.getContent().stream()
				.map(student -> modelMapper.map(student, StudentResponseDto.class)).collect(Collectors.toList());

		PageResponseDto<StudentResponseDto> response = new PageResponseDto<>();
		response.setContent(content);
		response.setPageNumber(studentPage.getNumber());
		response.setPageSize(studentPage.getSize());
		response.setTotalElements(studentPage.getTotalElements());
		response.setTotalPages(studentPage.getTotalPages());
		response.setLastPage(studentPage.isLast());

		return response;
	}

	@Override
	public StudentResponseDto getStudentById(Long id) {
		Student student = findStudentById(id);
		return modelMapper.map(student, StudentResponseDto.class);
	}

	@Override
	public StudentResponseDto updateStudent(Long id, StudentRequestDto requestDto) {
		Student student = findStudentById(id);
		StudentProfile profile = student.getProfile();

		if (studentProfileRepository.existsByEmailAndIdNot(requestDto.getProfile().getEmail(), profile.getId())) {
			throw new DuplicateResourceException("Email already exists: " + requestDto.getProfile().getEmail());
		}

		student.setFullName(requestDto.getFullName());
		student.setAge(requestDto.getAge());
		updateProfile(profile, requestDto.getProfile());

		Student updatedStudent = studentRepository.save(student);
		return modelMapper.map(updatedStudent, StudentResponseDto.class);
	}

	@Override
	public void deleteStudent(Long id) {
		Student student = findStudentById(id);
		studentRepository.delete(student);
	}

	private Student findStudentById(Long id) {
		return studentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
	}

	private void updateProfile(StudentProfile profile, StudentProfileRequestDto profileRequestDto) {
		profile.setEmail(profileRequestDto.getEmail());
		profile.setPhone(profileRequestDto.getPhone());
		profile.setCity(profileRequestDto.getCity());
	}

	private void validatePagination(int pageNumber, int pageSize) {
		if (pageNumber < 0) {
			throw new IllegalArgumentException("Page number must not be negative");
		}
		if (pageSize <= 0) {
			throw new IllegalArgumentException("Page size must be greater than 0");
		}
		if (pageSize > 100) {
			throw new IllegalArgumentException("Page size must not be greater than 100");
		}
	}
}
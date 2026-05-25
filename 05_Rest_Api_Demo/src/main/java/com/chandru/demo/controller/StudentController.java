package com.chandru.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chandru.demo.Exception.StudentNotFoundException;
import com.chandru.demo.entity.Student;
import com.chandru.demo.repository.StudentRepository;

@RestController
@RequestMapping("/api/students")
public class StudentController {

	private StudentRepository studentRepository;

	@Autowired
	public StudentController(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@PostMapping("/create")
	public Student createStudent(@RequestBody Student student) {
		return studentRepository.save(student);
	}

	@PostMapping("/createMultiple")
	public List<Student> createMultipleStudents(@RequestBody List<Student> student) {
		return studentRepository.saveAll(student);
	}

	@GetMapping("/{id}")
	public Optional<Student> getStudentById(@PathVariable int id) {
		Optional<Student> foundStudnent = studentRepository.findById(id);
		return foundStudnent;
	}

	@GetMapping("/getAll")
	public List<Student> getAllStudents() {
		List<Student> foundAllStudents = studentRepository.findAll();
		return foundAllStudents;
	}

	@PutMapping("updateName/{id}")
	public Student updateStudentName(@PathVariable int id, @RequestBody Student student) {

		Student foundStudent = studentRepository.findById(id).orElseThrow();

		foundStudent.setS_name(student.getS_name());

		return studentRepository.save(foundStudent);
	}

	@DeleteMapping("delete/{id}")
	public String deleteStudent(@PathVariable int id) {

		studentRepository.deleteById(id);

		return "Student deleted successfully";
	}

	@PutMapping("updateDept/{id}")
	public Student updateStudentDept(@PathVariable int id, @RequestBody Student student) {

		Student foundStudent = studentRepository.findById(id).orElseThrow();

		foundStudent.setS_department(student.getS_department());

		return studentRepository.save(foundStudent);

	}

	@PutMapping("updateDeptAndAge/{id}")
	public Student updateStudent(@PathVariable int id, @RequestBody Student updateStudent) {

		Student existingStudent = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));

		existingStudent.setS_age(updateStudent.getS_age());
		existingStudent.setS_department(updateStudent.getS_department());

		return studentRepository.save(existingStudent);
	}

	@PatchMapping("/{id}")
	public Student updatePartially(@PathVariable int id, @RequestBody Map<String, Object> updatedData) {
		Student existingStudent = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));

		if (updatedData.containsKey("std_name")) {
			existingStudent.setS_name((String) updatedData.get("std_name"));
		}
		if (updatedData.containsKey("std_age")) {
			existingStudent.setS_age((Integer) updatedData.get("std_age"));
		}
		return studentRepository.save(existingStudent);
	}
}

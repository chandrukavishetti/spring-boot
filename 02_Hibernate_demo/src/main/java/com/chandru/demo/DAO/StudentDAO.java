package com.chandru.demo.DAO;

import java.util.List;

import com.chandru.demo.model.Student;

public interface StudentDAO {

	public void save(Student theStudent);

	Student findById(Integer id);

	List<Student> findAll();
}

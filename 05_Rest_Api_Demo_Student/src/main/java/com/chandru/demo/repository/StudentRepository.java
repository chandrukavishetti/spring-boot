package com.chandru.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chandru.demo.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
package com.swabhav.demo.service;

import java.util.List;

import com.swabhav.demo.dto.CourseDetailResponseDTO;
import com.swabhav.demo.dto.CourseResponseDTO;
import com.swabhav.demo.dto.StudentResponseDTO;

public interface StudentService {

    List<StudentResponseDTO> getAllStudents();

    List<CourseResponseDTO> getCoursesByStudentId(Long studentId);

    CourseDetailResponseDTO getCourseDetailByCourseId(Long courseId);
}
package com.swabhav.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swabhav.demo.model.CourseDetail;

@Repository
public interface CourseDetailRepository extends JpaRepository<CourseDetail, Long> {

    Optional<CourseDetail> findByCourseId(Long courseId);

}
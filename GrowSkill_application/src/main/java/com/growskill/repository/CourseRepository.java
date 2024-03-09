package com.growskill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.growskill.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer>{

	
	
}

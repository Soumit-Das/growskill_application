package com.growskill.service;

import java.util.List;

import com.growskill.exception.CourseException;
import com.growskill.model.Course;

public interface CourseService {

	public Course createCourse(Course course) throws CourseException;
	
	public Course getCourseById(int id) throws CourseException;
	
	public List<Course> getAllCourses() throws CourseException;
	
	public Course updateCourseById(int id,Course course) throws CourseException;
	
	public String deleteCourseById(int id) throws CourseException;
	
}

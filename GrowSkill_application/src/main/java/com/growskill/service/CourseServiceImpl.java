package com.growskill.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.growskill.exception.CourseException;
import com.growskill.exception.InstructorException;
import com.growskill.model.Course;
import com.growskill.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService{

	@Autowired
	public CourseRepository courseRepository;
	
	@Override
	public Course createCourse(Course course) throws CourseException {
		
		if(courseRepository.existsById(course.getCourseId())) {
			throw new CourseException("Course already exists with id : "+course.getCourseId());
		}
		
		return courseRepository.save(course);
	}

	@Override
	public Course getCourseById(int id) throws CourseException {
		
		if(!courseRepository.existsById(id)) {
			throw new CourseException("Course doesn't exists with id : "+id);
		}
		
		Optional<Course> c = courseRepository.findById(id);

		return c.get();
	}

	@Override
	public List<Course> getAllCourses() throws CourseException {
		
		List<Course> courses = courseRepository.findAll();
		
		if(courses.isEmpty()) {
			throw new CourseException("No courses found");
		}
		
		return courses;
	}
	
	@Override
	public Course updateCourseById(int id, Course course) throws CourseException {
		
		if(!courseRepository.existsById(id)) {
			throw new CourseException("Course doesn't exists with id : "+id);
		}
		
		Optional<Course> c = courseRepository.findById(id);
		
		Course co = c.get();
		
		co.setBanner(course.getBanner());
		co.setDescription(course.getDescription());
		co.setDuration(course.getDuration());
		co.setEndingDate(course.getEndingDate());
		co.setStartingDate(course.getStartingDate());
		co.setPrice(course.getPrice());
		
		return courseRepository.save(co);
	}

	@Override
	public String deleteCourseById(int id) throws CourseException {
		
		if(!courseRepository.existsById(id)) {
			throw new CourseException("Course doesn't exists with id : "+id);
		}
		
		Optional<Course> c = courseRepository.findById(id);
		
		courseRepository.delete(c.get());
		
		return "Course deleted successfully";
	}

	

}

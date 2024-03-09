package com.growskill.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.growskill.exception.CourseException;
import com.growskill.exception.InstructorException;
import com.growskill.model.Course;
import com.growskill.model.Instructor;
import com.growskill.service.CourseService;
import com.growskill.service.InstructorService;

@RestController
@RequestMapping("/course")
public class CourseController {

	@Autowired
	public CourseService courseService;
	
	@Autowired
	public InstructorService instructorService;
	
	@PostMapping("/createCourse")
	public ResponseEntity<Course> createCourse(@RequestBody Course course,@RequestParam int instructorid) throws CourseException, InstructorException{
		
		Instructor inst = instructorService.getInstructorById(instructorid);
		
		if(inst == null) {
			throw new InstructorException("Instructor not found with id "+instructorid);
		}
		
		course.setInstructor(inst);
		
		Course c = courseService.createCourse(course);
		
		return new ResponseEntity<>(c,HttpStatus.CREATED);
	}
	
	@GetMapping("/getCourseById/{id}")
	public ResponseEntity<Course> getCourseById(@PathVariable int id) throws CourseException{
		
		Course c = courseService.getCourseById(id);
		
		return new ResponseEntity<>(c,HttpStatus.OK);

	}
	
	@GetMapping("/getAllCourses")
	public ResponseEntity<List<Course>> getAllCourses() throws CourseException{
		
		
		List<Course> courses = courseService.getAllCourses();
		
		return new ResponseEntity<>(courses,HttpStatus.OK);
		
	}
	
	@PutMapping("/updateCourseById/{id}")
	public ResponseEntity<Course> updateCourseById(@PathVariable int id,@RequestBody Course course) throws CourseException{
		
		Course c = courseService.updateCourseById(id, course);
		
		return new ResponseEntity<>(c,HttpStatus.OK);

	}
	
	@DeleteMapping("/deleteCourseById/{id}")
	public ResponseEntity<String> deleteCourseById(@PathVariable int id) throws CourseException{
		
		String c = courseService.deleteCourseById(id);
		
		return new ResponseEntity<>(c,HttpStatus.OK);
	}
	
}

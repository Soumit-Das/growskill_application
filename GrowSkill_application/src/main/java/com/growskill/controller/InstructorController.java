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
import com.growskill.model.Instructor;
import com.growskill.service.InstructorService;

@RestController
@RequestMapping("/instructor")
public class InstructorController {

	@Autowired
	public InstructorService instructorService; 
	
	@PostMapping("/createInstructor")
	public ResponseEntity<Instructor> createInstructor(@RequestBody Instructor instructor) throws InstructorException{
		
		Instructor inst = instructorService.createInstructor(instructor);
		
		return new ResponseEntity<>(inst,HttpStatus.CREATED);
	}
	
	@GetMapping("/getInstructorById/{id}")
	public ResponseEntity<Instructor> getInstructorById(@PathVariable int id) throws InstructorException{
		
		Instructor inst = instructorService.getInstructorById(id);
		
		return new ResponseEntity<>(inst,HttpStatus.OK);
		
	}
	
	@GetMapping("/getAllInstructors")
	public ResponseEntity<List<Instructor>> getAllInstructors() throws InstructorException{
		
		List<Instructor> inst = instructorService.getAllInstructors();
		
		return new ResponseEntity<>(inst,HttpStatus.OK);
		
	}
	
	@PutMapping("/updateInstructorById/{id}")
	public ResponseEntity<Instructor> updateInstructorById(@PathVariable int id,@RequestBody Instructor instructor) throws InstructorException{
		
		Instructor inst = instructorService.updateInstructorById(id, instructor);
		
		return new ResponseEntity<>(inst,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/deleteInstructorById/{id}")
	public ResponseEntity<String> deleteInstructorById(@PathVariable int id) throws InstructorException{
		
		String inst = instructorService.deleteInstructorById(id);
		
		return new ResponseEntity<>(inst,HttpStatus.OK);
		
	}
	
	@PostMapping("/assignCourse")
	public ResponseEntity<Instructor> assignCourseToInstructorByCourseIdAndInstructorId(@RequestParam int courseid,@RequestParam int instructorid)
			throws InstructorException, CourseException {
	
		Instructor inst = instructorService.assignCourseToInstructorByCourseIdAndInstructorId(courseid, instructorid);
		
		return new ResponseEntity<>(inst,HttpStatus.OK);
		
		
	}
	
	
}
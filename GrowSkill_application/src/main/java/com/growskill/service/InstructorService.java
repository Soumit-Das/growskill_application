package com.growskill.service;

import java.util.List;

import com.growskill.exception.CourseException;
import com.growskill.exception.InstructorException;
import com.growskill.model.Instructor;

public interface InstructorService {

	public Instructor createInstructor(Instructor instructor) throws InstructorException;
	
	public Instructor getInstructorById(int id) throws InstructorException;
	
	public List<Instructor> getAllInstructors() throws InstructorException;
	
	public Instructor updateInstructorById(int id,Instructor instructor) throws InstructorException;
	
	public String deleteInstructorById(int id) throws InstructorException;
	
	public Instructor assignCourseToInstructorByCourseIdAndInstructorId(int courseid,int instructorid) throws InstructorException,CourseException;
	
}

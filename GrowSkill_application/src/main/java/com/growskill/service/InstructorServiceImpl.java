package com.growskill.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.growskill.exception.CourseException;
import com.growskill.exception.InstructorException;
import com.growskill.model.Course;
import com.growskill.model.Instructor;
import com.growskill.repository.CourseRepository;
import com.growskill.repository.InstructorRepository;

@Service
public class InstructorServiceImpl implements InstructorService{

	@Autowired
	public InstructorRepository instructorRepository;
	
	@Autowired
	public CourseRepository courseRepository;
	
	@Override
	public Instructor createInstructor(Instructor instructor) throws InstructorException {
		
		if(instructorRepository.existsById(instructor.getInstructorId())) {
			throw new InstructorException("Instructor already exists with id : "+instructor.getInstructorId());
		}
		
		return instructorRepository.save(instructor);

	}

	@Override
	public Instructor getInstructorById(int id) throws InstructorException {
		
		if(!instructorRepository.existsById(id)) {
			throw new InstructorException("Instructor doesn't exists with id : "+id);
		}
		
		Optional<Instructor> inst = instructorRepository.findById(id);
		
		return inst.get();
	}

	@Override
	public List<Instructor> getAllInstructors() throws InstructorException {
		
		List<Instructor> instrList = instructorRepository.findAll();
		
		if(instrList.isEmpty()) {
			throw new InstructorException("No instructor found");
		}
		
		return instrList;
	}
	
	@Override
	public Instructor updateInstructorById(int id, Instructor instructor) throws InstructorException {
		
		if(!instructorRepository.existsById(id)) {
			throw new InstructorException("Instructor doesn't exists with id : "+id);
		}
		
		Optional<Instructor> inst = instructorRepository.findById(id);
		
		Instructor intstruct = inst.get();
		
		intstruct.setFirstname(instructor.getFirstname());
		intstruct.setLastname(instructor.getLastname());
		intstruct.setAddress(instructor.getAddress());
		intstruct.setAge(instructor.getAge());
		intstruct.setBio(instructor.getBio());
		intstruct.setDateofBirth(instructor.getDateofBirth());
		intstruct.setEmail(instructor.getEmail());
		intstruct.setExperience(instructor.getExperience());
		intstruct.setGender(instructor.getGender());
		intstruct.setJoiningDate(instructor.getJoiningDate());
		intstruct.setPassword(instructor.getPassword());
		intstruct.setPhone(instructor.getPhone());
		intstruct.setProfilePic(instructor.getProfilePic());
		intstruct.setSalary(instructor.getSalary());
		intstruct.setSpecializations(instructor.getSpecializations());
		intstruct.setRole(instructor.getRole());
		
		
		return instructorRepository.save(intstruct);
	}

	@Override
	public String deleteInstructorById(int id) throws InstructorException {
		
		if(!instructorRepository.existsById(id)) {
			throw new InstructorException("Instructor doesn't exists with id : "+id);
		}
		
		Optional<Instructor> inst = instructorRepository.findById(id);
		
		instructorRepository.delete(inst.get());
		
		return "Instructor deleted successfully";
	}

	@Override
	public Instructor assignCourseToInstructorByCourseIdAndInstructorId(int courseid, int instructorid)
			throws InstructorException, CourseException {
		
		if(!instructorRepository.existsById(instructorid)) {
			throw new InstructorException("Instructor doesn't exists with id : "+instructorid);
		}
		
		if(!courseRepository.existsById(courseid)) {
			throw new CourseException("Course doesn't exists with id : "+courseid);
		}
		
		Optional<Course> c = courseRepository.findById(courseid);
		
		Course co = c.get();
		
		Optional<Instructor> inst = instructorRepository.findById(instructorid);
		
		Instructor instr = inst.get();
		
		instr.getCourses().add(co);
		
		co.setInstructor(instr);
		
		return instr;
	}



}

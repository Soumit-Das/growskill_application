package com.growskill.service;

import com.growskill.exception.CourseException;
import com.growskill.exception.UserException;
import com.growskill.model.Course;
import com.growskill.model.User;
import java.util.*;


public interface UserService {

	public User createUser(User user) throws UserException;
	
	public void removeSessionMessage();
	
	public User getUserById(int id) throws UserException;
	
	public User updateUserById(int id,User user) throws UserException;
	
	public String deleteUserById(int id) throws UserException;
	
	public User assignCourseToUser(int courseid,int userid) throws UserException,CourseException;
	
}

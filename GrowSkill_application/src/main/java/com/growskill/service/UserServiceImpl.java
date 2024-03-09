package com.growskill.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.growskill.exception.CourseException;
import com.growskill.exception.UserException;
import com.growskill.model.Course;
import com.growskill.model.User;
import com.growskill.repository.CourseRepository;
import com.growskill.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	public CourseRepository courseRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public User createUser(User user) throws UserException {
		
		
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		
		user.setPassword(encodedPassword);
		user.setRole("ROLE_USER");
		User newuser = userRepository.save(user);
		
		return newuser;
	}

	@Override
	public void removeSessionMessage() {
		
		HttpSession session = ((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest().getSession();
		
		session.removeAttribute("msg");
	}
	
	@Override
	public User getUserById(int id) throws UserException {
		
		if(!userRepository.existsById(id)) {
			throw new UserException("User doesn't exists with id : "+id);
		}
		
		Optional<User> us = userRepository.findById(id);
		
		return us.get();
	}

	@Override
	public User updateUserById(int id, User user) throws UserException {
		
		if(!userRepository.existsById(id)) {
			throw new UserException("User doesn't exists with id : "+id);
		}
		
		Optional<User> us = userRepository.findById(id);
		
		User u = us.get();
		
		u.setFirstname(user.getFirstname());
		u.setLastname(user.getLastname());
		u.setAddress(user.getAddress());
		u.setAge(user.getAge());
		u.setDateofBirth(user.getDateofBirth());
		u.setEmail(user.getEmail());
		u.setGender(user.getGender());
		u.setPassword(user.getPassword());
		u.setPhone(user.getPhone());
		u.setProfilePic(user.getProfilePic());
		u.setRole(user.getRole());

		
		
		return userRepository.save(u);
	}

	@Override
	public String deleteUserById(int id) throws UserException {
		
		if(!userRepository.existsById(id)) {
			throw new UserException("User doesn't exists with id : "+id);
		}
		
		Optional<User> us = userRepository.findById(id);
		
		userRepository.delete(us.get());
		
		return "User deleted successfully";
	}

	@Override
	public User assignCourseToUser(int courseid, int userid) throws UserException, CourseException {
	
		if(!userRepository.existsById(userid)) {
			throw new UserException("User doesn't exists with id : "+userid);
		}
	
		if(!courseRepository.existsById(courseid)) {
			throw new CourseException("Course doesn't exists with id : "+courseid);
		}
		
		
		Optional<Course> c = courseRepository.findById(courseid);
		
		Course co = c.get();
		
		Optional<User> us = userRepository.findById(userid);
		
		User u = us.get();
		
		u.getCourses().add(co);
		co.getUsers().add(u);

		return u;

	}


}
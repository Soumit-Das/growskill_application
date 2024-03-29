package com.growskill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.growskill.exception.CourseException;
import com.growskill.exception.UserException;
import com.growskill.model.User;
import com.growskill.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	@Autowired
	public UserService userService;
	
	
	
	@PostMapping("/createUser")
	public String createUser(@ModelAttribute User user,HttpSession session) throws UserException{
		
		User us = userService.createUser(user);
		
		if(us != null){
			
			session.setAttribute("msg", "Register Successfully");
			
		}else{
			
			session.setAttribute("msg", "Something went wrong");
			
		}
		
		return "redirect:/register";
		
	}
	
	@GetMapping("/getUserById/{id}")
	public ResponseEntity<User> getUserById(@PathVariable int id) throws UserException{
		
		User us = userService.getUserById(id);
		
		return new ResponseEntity<>(us,HttpStatus.OK); 
		
	}
	
	@PutMapping("/updateUserById/{id}")
	public ResponseEntity<User> updateUserById(@PathVariable int id,@RequestBody User user) throws UserException{
		
		User us = userService.updateUserById(id, user);
		
		return new ResponseEntity<>(us,HttpStatus.OK); 
		
	}
	
	@DeleteMapping("/deleteUserById/{id}")
	public ResponseEntity<String> deleteUserById(@PathVariable int id) throws UserException{
		
		String us = userService.deleteUserById(id);
		
		return new ResponseEntity<>(us,HttpStatus.OK); 
		
	}
	
	@PostMapping("/assignCourseToUser")
	public ResponseEntity<User> assignCourseToUser(@RequestParam int courseid,@RequestParam int userid) throws UserException, CourseException{
		
		User us = userService.assignCourseToUser(courseid, userid);
		
		return new ResponseEntity<>(us,HttpStatus.OK); 
	}
	
}

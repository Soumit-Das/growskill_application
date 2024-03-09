package com.growskill.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.growskill.model.User;
import com.growskill.repository.UserRepository;

import org.springframework.ui.Model;

@Controller
public class HomeController {

	@Autowired
	public UserRepository userRepository;
	
	@ModelAttribute
	public void CommonUser(Principal p,Model m) {
		
		if(p != null) {
			
			String email = p.getName();
			
			User user = userRepository.findByEmail(email);
			
			m.addAttribute("user", user);
		}
		
		
	}
	
	@GetMapping("/")
	public String index() {
		
		return "index";
		
	}
	
	@GetMapping("/courses")
	public String courses() {
		
		return "courses";
		
	}
	
	@GetMapping("/coursesdetails")
	public String coursesdetails() {
		
		return "course_details";
		
	}
	
	@GetMapping("/profile")
	public String profile() {
		
		return "profile";
		
	}
	
	@GetMapping("/payments")
	public String payments() {
		
		return "payments";
		
	}
	
	@GetMapping("/classes")
	public String classes() {
		
		return "classes";
		
	}
	
	@GetMapping("/trainers")
	public String trainers() {
		
		return "trainers";
		
	}
	
	@GetMapping("/signin")
	public String login() {
		
		return "login";
		
	}
	
	@GetMapping("/register")
	public String register() {
		
		return "register";
		
	}
	
	@GetMapping("/admin")
	public String admin() {
		
		return "admin_dashboard";
		
	}
	
	@GetMapping("/adminproduct")
	public String adminproduct() {
		
		return "admin_product";
		
	}
	
}

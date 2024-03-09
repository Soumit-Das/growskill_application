package com.growskill.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int courseId;
	
	@Column(columnDefinition = "VARCHAR(255) DEFAULT 'https://img.pikbest.com/backgrounds/20210708/online-network-education-course-illustration-web-banner_6049725.jpg!bw700'")
	private String banner;
	
	private String subject;
	
	private String description;

	private int price;

	private String duration;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate startingDate;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate endingDate;
	
	@ManyToMany(mappedBy = "courses")
	@JsonIgnore
    private List<User> users;
	
	@ManyToOne
	@JoinColumn(name = "instructorId")
	private Instructor instructor;
	
}

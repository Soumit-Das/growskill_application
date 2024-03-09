package com.growskill.model;

import java.time.LocalDate;
import java.util.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Instructor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int instructorId;

	private String firstname;

	private String lastname;

	@Min(value = 18, message = "Age should be 18 or greater")
	private int age;
	
	@Enumerated(EnumType.STRING)
	private GenderTypeEnum gender; 

	private String address;
	
	@Email(message = "Invalid email format")
	@Column(unique = true)
	private String email;

	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%!]).{8,}$", message = "Password must have at least 8 characters, one uppercase letter, one lowercase letter, one number, and one special character")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

	@Digits(integer = 10, fraction = 0, message = "Phone number should have exactly 10 digits")
	@Column(unique = true)
	private long phone;

	private String bio;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate DateofBirth;

	@NotNull(message = "salary cannot be null")
	private int salary;
	
	private int experience;
	
	private String specializations;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate joiningDate;
	
	@Column(columnDefinition = "VARCHAR(255) DEFAULT 'https://png.pngtree.com/element_our/png/20181206/users-vector-icon-png_260862.jpg'")
	private String profilePic;
	
	@Column(name = "role", nullable = true, columnDefinition = "VARCHAR(255) DEFAULT 'instructor'")
	private String role;
	
	@OneToMany(mappedBy = "instructor",cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Course> courses;
	
}

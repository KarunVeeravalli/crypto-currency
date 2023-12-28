package com.cc.dto;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Data
public class UnAuthUser {
	
	private Integer id;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String panCardNumber;
	
	private Long aadhaarNumber;
	
	private String password;
	
	private Long mobileNumber;
	
	private LocalDateTime createdAt;
	
	private String about;
	
	private String status;
	
    private String image;
	
	private String panCard;
	
	private String aadhaarCard;
 	
	
}

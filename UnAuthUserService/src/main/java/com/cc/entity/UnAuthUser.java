package com.cc.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Data
@Table(name = "UNAUTHUSER", uniqueConstraints = @UniqueConstraint(columnNames = {"email","panCardNumber","aadhaarNumber"
		,"mobileNumber"}))
public class UnAuthUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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

package com.cc.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.transaction.Transaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Users {
	
	private String username;
	
	private String firstname;
	
	private Long mobileNumber;
	
	private Long aadhar;
	
	private String panNum;
	
	private String email;
	
	@JsonIgnore
	private String encodedPassword;

	private String role;
	
    private String image;
	
	private String panCard;
	
	private String aadhaarCard;

}

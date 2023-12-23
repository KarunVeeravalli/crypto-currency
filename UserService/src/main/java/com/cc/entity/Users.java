package com.cc.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(
        name="USERS", 
        uniqueConstraints=
            @UniqueConstraint(columnNames={"username", "email"})
    )

public class Users {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(unique = true)
	private String username;
	
	private String firstname;
	
	private Long mobileNumber;
	
	private Long aadhar;
	
	private String panNum;
	
	private String email;
	
	@JsonIgnore
	private String encodedPassword;
	
	@JsonIgnore
	private LocalDateTime createdTimeStamp;
	
	private String role;
	
	@Transient
	private List<Bank> bank = new ArrayList<>();
	
	@Transient
	private Wallet wallet = new Wallet();
	
	@Transient
	private CoinWallet coinWallet ;
	
	@Transient
	private List<Transaction> transactions =new ArrayList<>();
	
    private String image;
	
	private String panCard;
	
	private String aadhaarCard;

}

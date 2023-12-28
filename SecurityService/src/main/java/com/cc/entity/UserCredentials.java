package com.cc.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Data
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class UserCredentials {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String email;
	
	private String password;

}




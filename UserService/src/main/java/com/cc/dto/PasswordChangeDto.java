package com.cc.dto;

import lombok.Data;

@Data
public class PasswordChangeDto {
	
	private String email;
	
	private String currentPassword;
	
	private String newPassword;

}

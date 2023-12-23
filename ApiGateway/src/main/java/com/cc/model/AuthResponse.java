package com.cc.model;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
	
	private String userId;
	
	private String accessToken;
	
	private String refreshToken;
	
	private Long expiredAt;

    private Collection<String> authorities;
	
}

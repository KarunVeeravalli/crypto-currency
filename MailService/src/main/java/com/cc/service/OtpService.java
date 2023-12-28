package com.cc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cc.dto.OtpDto;
import com.cc.entity.OtpEntity;
import com.cc.repository.OtpRepository;

@Service
public class OtpService {
	
	@Autowired
	OtpRepository repository;
	
	public void saveOtp(OtpEntity entity) {
		repository.save(entity);
	}
	
	public OtpEntity getLastOtp(String email) {
		List<OtpEntity> otps = repository.getLastOtp(email);
		return otps.get(0);
	}

	public HttpStatus checkOtp(OtpDto dto) {
		String otp = getLastOtp(dto.getEmail()).getOtp();
		if(dto.getOtp().equals(otp)) {
			return HttpStatus.ACCEPTED;
		}
		return HttpStatus.CONFLICT;
	}

}

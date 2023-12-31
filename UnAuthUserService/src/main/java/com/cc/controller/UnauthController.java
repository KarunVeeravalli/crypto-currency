package com.cc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cc.dto.RegistrationDto;
import com.cc.entity.UnAuthUser;
import com.cc.exception.AdminException;
import com.cc.exception.UnAuthUserException;
import com.cc.service.UnAuthUserService;

import jakarta.ws.rs.Path;

@RestController
@RequestMapping("/unauth")
public class UnauthController {
	
	@Autowired
	UnAuthUserService service;
	
	@PostMapping("/addUnAuthUser")
	public UnAuthUser addUnAuthUser(@RequestBody RegistrationDto user) throws UnAuthUserException{
		return service.addUnAuthUser(user);
	}
	
	@GetMapping("/getUnAuthUserById/{id}")
	public UnAuthUser getUnAuthUserById(@PathVariable Integer id) throws UnAuthUserException{
		return service.getUnAuthUserById(id);
	}
	
	@GetMapping("/getUnAuthUserByEmail/{mail}")
	public UnAuthUser getUnAuthUserByEmail(@PathVariable String mail) throws UnAuthUserException{
		return service.getUnAuthUserByEmail(mail);
	}
	
	@GetMapping("/getUnAuthUserByStatus/{status}")
	public List<UnAuthUser> getUnauAuthUserByStatus(@PathVariable String status) throws UnAuthUserException, AdminException{
		return service.getUnauAuthUserByStatus(status);
	}
	
	@DeleteMapping("/deleteUnAuthUserById/{id}")
	public UnAuthUser deleteUnAuthUserById(@PathVariable Integer id) throws UnAuthUserException{
		return service.deleteUnAuthUserById(id);
	}
	
	@PutMapping("/rejectUserById/{id}")
	public String rejectUserById(@PathVariable Integer id) throws UnAuthUserException{
		return service.rejectUserById(id);
	}
	
	@PutMapping("/updateUnauthUser/{id}")
	public UnAuthUser updateUnauthUserById(@PathVariable Integer id, @RequestBody RegistrationDto dto) throws UnAuthUserException {
		return service.updateUserById(id, dto);
	}
}

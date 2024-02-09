package com.cc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cc.dto.LoginDto;
import com.cc.dto.PasswordChangeDto;
import com.cc.dto.UsersDto;
import com.cc.entity.Users;
import com.cc.exception.AdminException;
import com.cc.exception.UnAuthUserException;
import com.cc.exception.UsersException;
import com.cc.service.UsersService;
import com.cc.utilityHelper.GeneralResponse;

import io.github.resilience4j.retry.annotation.Retry;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UsersService service;
	
	@GetMapping("/getUser/{id}")
   @Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<GeneralResponse> getUser( @PathVariable Integer id,HttpServletRequest request) throws UsersException{
		GeneralResponse generalResponse = new GeneralResponse();
		generalResponse.setMessage("User was Found by user Id: "+id);
		generalResponse.setData(service.getUser(id,request));
		return ResponseEntity.ok(generalResponse);
	}
	
	@GetMapping("/getOnlyUser/{id}")
	public UsersDto getOnlyUser(@PathVariable Integer id,HttpServletRequest request) throws UsersException{
		return service.getOnlyUserDetailsDto(id,request);
	}
	

	@GetMapping("/getOnlyUser")
	public UsersDto getOnlyUser(HttpServletRequest request) throws UsersException{
		return service.getOnlyUserDetailsDto(request);
	}
	
	
	 public ResponseEntity<GeneralResponse> ratingHotelFallback(String userId, Exception ex) {
       ex.printStackTrace();
       GeneralResponse generalResponse = new GeneralResponse();
	   generalResponse.setMessage("User was Found by user Id: "+userId);
	   generalResponse.setData("Sorry bro service down, try after some time");
      return ResponseEntity.ok(generalResponse);
    }
	
	@PostMapping("/addUser")
	public Users addUser(@RequestBody UsersDto dto,HttpServletRequest request) throws UsersException{
		return (service.addUser(dto,request));
	}
	
	@PutMapping("/updateUser/{id}")
	public ResponseEntity<GeneralResponse> updateUser(@RequestBody UsersDto newUserDetails,@PathVariable Integer id,HttpServletRequest request) throws UsersException{
		GeneralResponse generalResponse = new GeneralResponse();
		generalResponse.setMessage("User was Updated by user Id: "+id);
		generalResponse.setData(service.updateUser(newUserDetails, id,request));
		return ResponseEntity.ok(generalResponse);
	}
	
	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<GeneralResponse> deleteUser(@PathVariable Integer id,HttpServletRequest request) throws UsersException{
		GeneralResponse generalResponse = new GeneralResponse();
		generalResponse.setMessage("User was deleted by user Id: "+id);
		generalResponse.setData(service.deleteUser(id,request));
		return ResponseEntity.ok(generalResponse);
	}
	
	@GetMapping("/getAllUsers")
	public ResponseEntity<GeneralResponse> getAllUsers(HttpServletRequest request) throws UsersException{
		GeneralResponse generalResponse = new GeneralResponse();
		generalResponse.setMessage("Find the below users");
		generalResponse.setData(service.getAllUsers(request));
		return ResponseEntity.ok(generalResponse);
	}
	
	@PutMapping("/changePassword/{id}")
	public ResponseEntity<GeneralResponse> changePassword(@RequestBody PasswordChangeDto dto,@PathVariable Integer id,HttpServletRequest request) throws UsersException{
		GeneralResponse generalResponse = new GeneralResponse();
		generalResponse.setMessage("User password was changed by user Id: "+id);
		generalResponse.setData(service.changePassword(dto,id,request));
		return ResponseEntity.ok(generalResponse);
	}
	
	@GetMapping("/getAllUnAuthUsers/{id}")
	public ResponseEntity<GeneralResponse> getAllUnAuthUsers(@PathVariable Integer id,HttpServletRequest request) throws UsersException,AdminException {
		GeneralResponse generalResponse = new GeneralResponse();
		generalResponse.setMessage("UnAUthUsers Found with Admin ID : "+id);
		generalResponse.setData(service.getAllUnAuthUsers(id,request));
		return ResponseEntity.ok(generalResponse);
	}
	
	@PostMapping("/addUserByUnauthUser/{id}/{unauthId}")
	public Users addUserByUnauthUser(@PathVariable Integer id,@PathVariable Integer unauthId,HttpServletRequest request) throws UsersException,UnAuthUserException,AdminException{
		return service.addUserByUnauthUser(id, unauthId,request);
	}
	
	@PutMapping("/rejectUnAuthUser/{id}/{unauthId}")
	public String rejectUnAuthUser(@PathVariable Integer id,@PathVariable Integer unauthId,HttpServletRequest request) throws UsersException, UnAuthUserException, AdminException {
		return service.rejectUnAuthUser(id, unauthId,request);
	}
	
	@GetMapping("/getCredentials/{email}")
	public LoginDto getCredentials(@PathVariable String email,HttpServletRequest request) throws UsersException{
		return service.getCredentials(email,request);
	}
	
	
}

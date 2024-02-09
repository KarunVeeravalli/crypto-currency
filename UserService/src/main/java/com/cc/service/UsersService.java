package com.cc.service;

import java.util.List;

import com.cc.dto.LoginDto;
import com.cc.dto.PasswordChangeDto;
import com.cc.dto.UsersDto;
import com.cc.entity.UnAuthUser;
import com.cc.entity.Users;
import com.cc.exception.AdminException;
import com.cc.exception.UnAuthUserException;
import com.cc.exception.UsersException;

import jakarta.servlet.http.HttpServletRequest;

public interface UsersService {
	
	public Users getOnlyUserDetails(Integer id,HttpServletRequest request) throws UsersException;
	
	public Users getUser(Integer id,HttpServletRequest request) throws UsersException;
	
	public Users addUser(UsersDto user,HttpServletRequest request) throws UsersException;
	
	public Users updateUser(UsersDto newUserDetails,Integer id,HttpServletRequest request) throws UsersException;
	
	public Users deleteUser(Integer id,HttpServletRequest request) throws UsersException;
	
	public List<Users> getAllUsers(HttpServletRequest request) throws UsersException;
	
	public Users changePassword(PasswordChangeDto dto,Integer id,HttpServletRequest request) throws UsersException;
	
	public List<UnAuthUser> getAllUnAuthUsers(Integer id,HttpServletRequest request) throws UsersException,AdminException;
	
	public Users addUserByUnauthUser(Integer id, Integer unuathId,HttpServletRequest request) throws UsersException,UnAuthUserException,AdminException;
	
	public String rejectUnAuthUser(Integer id,Integer unauthId,HttpServletRequest request) throws UsersException,UnAuthUserException,AdminException;

	public LoginDto getCredentials(String email,HttpServletRequest request) throws UsersException;

	public UsersDto getOnlyUserDetailsDto(Integer id,HttpServletRequest request) throws UsersException;

	public UsersDto getOnlyUserDetailsDtoByUserName(String userName,HttpServletRequest request) throws UsersException;

	public UsersDto getOnlyUserDetailsDto(HttpServletRequest request) throws UsersException;


}

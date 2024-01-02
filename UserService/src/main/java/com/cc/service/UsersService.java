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

public interface UsersService {
	
	public Users getOnlyUserDetails(Integer id) throws UsersException;
	
	public Users getUser(Integer id) throws UsersException;
	
	public Users addUser(UsersDto user) throws UsersException;
	
	public Users updateUser(UsersDto newUserDetails,Integer id) throws UsersException;
	
	public Users deleteUser(Integer id) throws UsersException;
	
	public List<Users> getAllUsers() throws UsersException;
	
	public Users changePassword(PasswordChangeDto dto,Integer id) throws UsersException;
	
	public List<UnAuthUser> getAllUnAuthUsers(Integer id) throws UsersException,AdminException;
	
	public Users addUserByUnauthUser(Integer id, Integer unuathId) throws UsersException,UnAuthUserException,AdminException;
	
	public String rejectUnAuthUser(Integer id,Integer unauthId) throws UsersException,UnAuthUserException,AdminException;

	public LoginDto getCredentials(String email) throws UsersException;

	public UsersDto getOnlyUserDetailsDto(Integer id) throws UsersException;

	public UsersDto getOnlyUserDetailsDtoByUserName(String userName) throws UsersException;
}

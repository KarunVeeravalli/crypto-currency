package com.cc.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cc.dto.LoginDto;
import com.cc.dto.PasswordChangeDto;
import com.cc.dto.UsersDto;
import com.cc.entity.Bank;
import com.cc.entity.CoinWallet;
import com.cc.entity.Transaction;
import com.cc.entity.UnAuthUser;
import com.cc.entity.Users;
import com.cc.entity.Wallet;
import com.cc.exception.AdminException;
import com.cc.exception.UnAuthUserException;
import com.cc.exception.UsersException;
import com.cc.feign.BankExternalServices;
import com.cc.feign.CoinWalletExternalService;
import com.cc.feign.TransactionExternalService;
import com.cc.feign.UnAuthUserExternalService;
import com.cc.feign.WalletExternalService;
import com.cc.repository.UserRepository;
import com.cc.utilityHelper.GeneralResponse;
import com.cc.utilityHelper.RepoHelper;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class UsersServiceImpl implements UsersService {
	
	@Autowired
	UserRepository repository;
	
	@Autowired
	BankExternalServices externalServices;
	
	@Autowired
	WalletExternalService walletExternalService;
	
	@Autowired
	CoinWalletExternalService coinWalletExternalService;
	
	@Autowired
	TransactionExternalService transactionExternalService;
	
	@Autowired
	UnAuthUserExternalService unAuthUserExternalService;
	
	@Autowired
	RepoHelper helper;

	@Override
	public Users getUser(Integer id,HttpServletRequest request) throws UsersException {
		
		
		Users user = repository.findById(id).get();
		List<Bank> banks = externalServices.getAllBanksByUserId(id); 
		Wallet wallet = walletExternalService.getWalletByUserId(id);
		CoinWallet coinWallet = coinWalletExternalService.getCoinWalletByUserId(id);
		ResponseEntity<GeneralResponse> transactionResponse = transactionExternalService.getAllTransactionsByUserId(id);
		List<Transaction> transactions = (List<Transaction>) transactionResponse.getBody().getData();
		user.setWallet(wallet);
		user.setBank(banks);
		user.setCoinWallet(coinWallet);
		user.setTransactions(transactions);
		return user;		
	}
	@Override
	public Users addUser(UsersDto dto,HttpServletRequest request) throws UsersException {
		Users user = new Users();
//		user.setUsername(dto.getFirstname()+dto.getMobileNumber().toString().substring(0, 3));
//		user.setAadhar(dto.getAadhar());
		user.setEncodedPassword(dto.getPassword());
		user.setEmail(dto.getEmail());
//		user.setFirstname(dto.getFirstname());
//		user.setPanNum(dto.getPanNum());
//		user.setMobileNumber(dto.getMobileNumber());
		user.setCreatedTimeStamp(LocalDateTime.now());
		user.setRole("general");
//		user.setAadhaarCard(dto.getAadhaarCard());
//		user.setImage(dto.getImage());
//		user.setPanCard(dto.getPanCard());
		Users newUser = repository.save(user);
		user.setWallet(walletExternalService.addWallet(newUser.getId()));
		user.setCoinWallet(coinWalletExternalService.addCoinWallet(newUser.getId()));
		return user;
	}

	@Override
	public Users updateUser(UsersDto newUserDetails, Integer id,HttpServletRequest request) throws UsersException {
		Users oldUserDetails = getUser(id,request); 
		oldUserDetails.setUsername(newUserDetails.getFirstname()+newUserDetails.getMobileNumber().toString().substring(0, 3));;
		BeanUtils.copyProperties(newUserDetails, oldUserDetails, helper.getNullPropertyNames(newUserDetails));
		return repository.save(oldUserDetails);
	}

	@Override
	public Users deleteUser(Integer id,HttpServletRequest request) throws UsersException {
		Users user = getUser(id,request);
		walletExternalService.deleteWalletById(user.getWallet().getId());
		coinWalletExternalService.deleteCoinWallet(user.getCoinWallet().getId());
		repository.deleteById(id);
		return user;
	}

	@Override
	public List<Users> getAllUsers(HttpServletRequest request) throws UsersException {
		return repository.findAll();
	}

	@Override
	public Users changePassword(PasswordChangeDto dto,Integer id,HttpServletRequest request) throws UsersException {
		Users user = getUser(id,request);
	 	 if(dto.getCurrentPassword().equals(user.getEncodedPassword())) {
				user.setEncodedPassword(dto.getNewPassword());
	 	 }
	 	 return repository.save(user);
	}
	@Override
	public List<UnAuthUser> getAllUnAuthUsers(Integer id,HttpServletRequest request) throws UsersException,AdminException {
		Users admin = getOnlyUserDetails(id,request);
		if(admin.getRole().equals("ADMIN")) {

			return unAuthUserExternalService.getUnauAuthUserByStatus("PENDING");
		}else {
			throw new AdminException("Admin not found with id: "+id);
		}
	}
	@Override
	public Users addUserByUnauthUser(Integer id, Integer unauthId,HttpServletRequest request)
			throws UsersException, UnAuthUserException, AdminException {
		UnAuthUser unAuthUser = unAuthUserExternalService.getUnAuthUserById(unauthId);
		if(unAuthUser.equals(null)) {
			throw new UnAuthUserException("UnAuthUser is not found with id: "+unauthId);
		}
		Users admin =  getOnlyUserDetails(id,request);
		if(admin.getRole().equals("ADMIN") ){
			Users user =  new Users();
			user.setFirstname(unAuthUser.getFirstName());
			user.setUsername(unAuthUser.getFirstName()+unAuthUser.getMobileNumber().toString().substring(0, 3));
			user.setAadhar(unAuthUser.getAadhaarNumber());
			user.setCreatedTimeStamp(LocalDateTime.now());
			user.setEmail(unAuthUser.getEmail());
			user.setMobileNumber(unAuthUser.getMobileNumber());
			user.setPanNum(unAuthUser.getPanCardNumber());
			user.setRole("GENERAL");
			user.setImage(unAuthUser.getImage());
			user.setPanCard(unAuthUser.getPanCard());
			user.setAadhaarCard(unAuthUser.getAadhaarCard());
			Users newUser = repository.save(user);
			user.setWallet(walletExternalService.addWallet(newUser.getId()));
			user.setCoinWallet(coinWalletExternalService.addCoinWallet(newUser.getId()));
			unAuthUserExternalService.deleteUnAuthUserById(unauthId);
			return user;
		}else {
			throw new AdminException("Admin not found with id: "+id);
		}
		
	}
	@Override
	public String rejectUnAuthUser(Integer id, Integer unauthId,HttpServletRequest request)
			throws UsersException, UnAuthUserException, AdminException {
		Users admin = getUser(id,request);
		if(admin.getRole().equals("ADMIN")) {
			String msg = unAuthUserExternalService.rejectUserById(unauthId);
			return msg+" the coustomer by id: "+id;
		}else {
			throw new AdminException("Admin not found with id: "+id);
		}	
	}
	@Override
	public LoginDto getCredentials(String email,HttpServletRequest request) throws UsersException {
		Users user = repository.findByEmail(email);
		LoginDto dto = new LoginDto();
		dto.setEmail(user.getEmail());
		dto.setPassword(user.getEncodedPassword());
		return dto;
	}
	@Override
	public Users getOnlyUserDetails(Integer id,HttpServletRequest request) throws UsersException {
		return repository.findById(id).get();
	}
	@Override
	public UsersDto getOnlyUserDetailsDto(Integer id,HttpServletRequest request) throws UsersException {
		UsersDto dto = new UsersDto();
		Users user = getOnlyUserDetails(id,request);
		dto.setEmail(user.getEmail());
		dto.setMobileNumber(user.getMobileNumber());
		dto.setPanNum(user.getPanNum());
		if(user==null) {
			return null;
		}
		return dto;
	}
	@Override
	public UsersDto getOnlyUserDetailsDtoByUserName(String userName,HttpServletRequest request) throws UsersException {
		Users user = repository.findByUsername(userName);
		UsersDto dto = new UsersDto();
		dto.setEmail(user.getEmail());
		dto.setMobileNumber(user.getMobileNumber());
		dto.setPanNum(user.getPanNum());
		return dto;
	}
	@Override
	public UsersDto getOnlyUserDetailsDto(HttpServletRequest request) throws UsersException {
		String email  = helper.getUsernameFromToken(request);
		UsersDto dto = new UsersDto();
		Users user = repository.findByEmail(email);
		dto.setEmail(user.getEmail());
		dto.setMobileNumber(user.getMobileNumber());
		dto.setPanNum(user.getPanNum());
		dto.setId(user.getId());
		return dto;
	}
	
	

}

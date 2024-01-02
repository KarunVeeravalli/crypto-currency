package com.cc.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cc.dto.AuthRequest;
import com.cc.dto.EmailDto;
import com.cc.dto.OtpDto;
import com.cc.dto.RegistrationDto;
import com.cc.entity.UnauthUser;
import com.cc.entity.UserCredentials;
import com.cc.feignClient.EmailExternalService;
import com.cc.feignClient.UnAuthUserExternalService;
import com.cc.service.AuthService;
import com.cc.service.UnauathUserService;

import jakarta.ws.rs.Consumes;

@RestController
@RequestMapping("/auth" )
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Consumes(MediaType.APPLICATION_JSON_VALUE)
public class SecurityController {
    @Autowired
    private AuthService service;
    
    @Autowired
    private EmailExternalService emailExternalService;
    
    @Autowired
    private UnAuthUserExternalService unAuthUserExternalService;
    
    @Autowired
    private UnauathUserService unauathUserService;
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public String addNewUser(@RequestBody UnauthUser user) {
    	EmailDto dto =  new EmailDto();
    	dto.setSubject("One Time Password for Accessing your Coin Currency");
    	dto.setToEmail(user.getEmail());
    	emailExternalService.sendOtp(dto);
    	unauathUserService.saveUser(user);
        return "Otp was send to the email: "+user.getEmail();
    }
    
    @PutMapping("/verifyOtp")
    public String checkOtp(@RequestBody OtpDto dto) {
    	HttpStatus status = emailExternalService.checkOtp(dto);
    	if(status.equals(HttpStatus.ACCEPTED)) {
    		UnauthUser unAuthUser = unauathUserService.getUserByEmail(dto.getEmail());
    		UserCredentials credentials = new UserCredentials();
    		credentials.setEmail(unAuthUser.getEmail());
    		credentials.setPassword(unAuthUser.getPassword());
    		service.saveUser(credentials);
    		unauathUserService.deleteUser(unAuthUser.getId());
    		RegistrationDto regDto = new RegistrationDto();
    		regDto.setEmail(dto.getEmail());
    		regDto.setPassword(credentials.getPassword());
    		unAuthUserExternalService.addUnAuthUser(regDto);
    		return "new UnAuthUser was added ";
    	}
    	else {
    		return "retry again, otp is incorrect";
    	}
    }
    
    @CrossOrigin
    @PostMapping("/token")
    public String getToken(@RequestBody AuthRequest authRequest) {
    	System.out.println(LocalDateTime.now()+"in token method");
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            return service.generateToken(authRequest.getUsername());
        } else {
            throw new RuntimeException("invalid access");
        }
    }
    
    @GetMapping("/name")
    public List<UserCredentials> getAllUsers(){
    	return service.getAllUsers();
    }
    
    
    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        service.validateToken(token);
        return "Token is valid";
    }
}
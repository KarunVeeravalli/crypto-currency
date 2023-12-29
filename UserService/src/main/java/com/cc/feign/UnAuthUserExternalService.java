package com.cc.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.cc.entity.UnAuthUser;
import com.cc.utilityHelper.GeneralResponse;

@FeignClient("UNAUTHUSER-SERVICE")
public interface UnAuthUserExternalService {
	
	
	@GetMapping("/unauth/getUnAuthUserByStatus/{status}")
	public List<UnAuthUser> getUnauAuthUserByStatus(@PathVariable String status) ;
	
	@GetMapping("/unauth/getUnAuthUserById/{id}")
	public UnAuthUser getUnAuthUserById(@PathVariable Integer id);
	
	@DeleteMapping("/unauth/deleteUnAuthUserById/{id}")
	public UnAuthUser deleteUnAuthUserById(@PathVariable Integer id) ;
	
//	@DeleteMapping("/unauth/deleteUser/{id}")
//	public ResponseEntity<GeneralResponse> deleteUser(@PathVariable Integer id) ;
	
	@PutMapping("/unauth/rejectUserById/{id}")
	public String rejectUserById(@PathVariable Integer id);
}

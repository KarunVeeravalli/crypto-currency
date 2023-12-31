package com.cc.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cc.entity.Transaction;
import com.cc.utilityHelper.GeneralResponse;

@FeignClient("TRANSACTION-SERVICE")
public interface TransactionExternalService {
	
	@PostMapping("/transaction/addTransaction")
	public ResponseEntity<GeneralResponse> addTransaction(@RequestBody Transaction transactions) ;

}

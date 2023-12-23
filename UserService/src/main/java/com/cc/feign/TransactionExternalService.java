package com.cc.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cc.utilityHelper.GeneralResponse;

@FeignClient("TRANSACTION-SERVICE")
public interface TransactionExternalService {
	
	@GetMapping("/transaction/getAllTransactionsByUserId/{userId}")
	public ResponseEntity<GeneralResponse> getAllTransactionsByUserId(@PathVariable Integer userId);

}

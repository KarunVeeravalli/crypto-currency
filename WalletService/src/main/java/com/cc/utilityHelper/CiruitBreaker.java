package com.cc.utilityHelper;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;

@Configuration
public class CiruitBreaker {
	
	 @Bean
	    public CircuitBreaker circuitBreaker() {
	        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
	                .failureRateThreshold(50) 
	                .waitDurationInOpenState(Duration.ofMillis(1000))
	                .build();
	        return CircuitBreaker.of("TRANSACTION-SERVICE", config);
	    }

}

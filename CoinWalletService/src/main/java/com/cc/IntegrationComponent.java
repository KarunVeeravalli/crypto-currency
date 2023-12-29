//package com.cc;
//
//import java.util.Map;
//
//import org.springframework.stereotype.Component;
//
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
//
//	
//	@Component
//	public class IntegrationComponent{
//		
//		@HystrixCommand(fallbackMethod = "defaultStores")
//	    public Object getCoins(Map<String, Object> parameters) {
//	        //do stuff that might fail
//			return null;
//	    }
//
//	    public Object defaultStores(Map<String, Object> parameters) {
////	        return /* something useful */;
//	    	return null;
//	    }
//	}
//
//

package com.demo.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {

	public static ResponseEntity<Object> generateResponse(HttpStatus status, boolean success,String message, Object result) {
		Map<String,Object> response=new HashMap<>();
		response.put("timeStamp", new Date());
		response.put("message", message);
		response.put("success", success);
		response.put("status", status.value());
		response.put("body", response);
		return new ResponseEntity<>(response,status);
	}
	
}

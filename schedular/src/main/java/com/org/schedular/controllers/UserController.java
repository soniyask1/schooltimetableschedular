package com.org.schedular.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.org.schedular.models.User;
import com.org.schedular.response.ServiceResponse;
import com.org.schedular.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private ObjectMapper objectMapper;
	@GetMapping("/all")
	public ResponseEntity<String> getUsers() {
		ServiceResponse<List<User>, String> response = new ServiceResponse<List<User>, String>();
		response.setCode("SUCCESS");
		response.setResult(userService.getUsers());
		response.setStatus(true);
		String ocReturn="";
		try {
			objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
			ocReturn = objectMapper.writeValueAsString(response);
		} catch (Exception e) {
			System.out.println("Unable to create Response");
			return ResponseEntity.status(500).build();
		} 
		return ResponseEntity.ok(ocReturn);
	}
	
	@GetMapping("/current")
	public ResponseEntity<String> getLoggedInUser(Principal principal){
		return ResponseEntity.ok(principal.getName());
	}

}

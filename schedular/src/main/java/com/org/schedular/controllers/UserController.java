package com.org.schedular.controllers;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.schedular.models.User;
import com.org.schedular.response.ServiceResponse;
import com.org.schedular.services.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private UserService userService;
	@GetMapping("/all")
	public ResponseEntity<String> getUsers(HttpServletRequest request) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		ServiceResponse<List<User>, String> response = new ServiceResponse<List<User>, String>();
		response.setCode("SUCCESS");
		response.setResult(userService.getUsers());
		response.setStatus(true);
		try {
			return ResponseEntity.ok(ControllerUtility.getJsonFromResponseObject(response));
		} catch (Exception e) {
			logger.error(methodName + " - Unable to create Response");
			return ResponseEntity.status(500).build();
		}		
	}
	
	@GetMapping("/current")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> getLoggedInUser(Principal principal){
		return ResponseEntity.ok(principal.getName());
	}

}

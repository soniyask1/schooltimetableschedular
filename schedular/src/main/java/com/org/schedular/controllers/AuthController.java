package com.org.schedular.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.org.schedular.dto.JwtRequest;
import com.org.schedular.security.JwtHelper;

@RestController
public class AuthController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtHelper jwtHelper;
	
	@PostMapping("/login")
	public <T> ResponseEntity<T> login(@RequestBody JwtRequest request) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		logger.info(methodName + " - Authentication initiated.");
		doAuthenticare(request.getUsername(), request.getPassword());
		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
		String token = jwtHelper.generateToken(userDetails);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + token);
		return new ResponseEntity<T>(headers, HttpStatus.OK);
	}
	
	private void doAuthenticare(String username, String password) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
		try {
			authenticationManager.authenticate(token);
		} catch (BadCredentialsException ex) {
			throw new RuntimeException("Invalid User");
		}
	}
}

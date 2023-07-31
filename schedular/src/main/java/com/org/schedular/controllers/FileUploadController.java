package com.org.schedular.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.org.schedular.response.ServiceResponse;
import com.org.schedular.services.FileUploadService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class FileUploadController {
	@Autowired
	private FileUploadService csvUploadService;
	@Autowired
	private ObjectMapper objectMapper;
	
	
	@PostMapping("/uploadFile")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		ServiceResponse<String, String> response = csvUploadService.saveFilesOnTheServer(file, request);
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
}

package com.org.schedular.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.org.schedular.response.ServiceResponse;

public class ControllerUtility {
	
	public static <T, E>String getJsonFromResponseObject(ServiceResponse<T, E> serviceResponse) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		String ocReturn="";
		objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
		ocReturn = objectMapper.writeValueAsString(serviceResponse);
		return ocReturn;
	}

}

package com.org.schedular.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.org.schedular.models.User;

@Service
public class UserService {
	private List<User> users = new ArrayList<User>();
	
	public UserService() {
		users.add(new User(UUID.randomUUID().toString(), "Soniya", "soniya@ekdant.com"));
		users.add(new User(UUID.randomUUID().toString(), "Aditi", "Aditi@ekdant.com"));
	}
	
	public List<User> getUsers(){
		return users;
	}

}

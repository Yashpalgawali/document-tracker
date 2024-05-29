package com.example.demo.service;

import java.util.List;

import com.example.demo.models.User;

public interface UserService {

	public User saveUser(User user);
	
	public List<User> getAllUsers();
}

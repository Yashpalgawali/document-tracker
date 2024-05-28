package com.example.demo.service;

import java.util.List;

import com.example.demo.models.UserType;

public interface UserTypeService {

	public UserType saveUserType(UserType usertype);
	
	public List<UserType> getAllUserTypes();
}

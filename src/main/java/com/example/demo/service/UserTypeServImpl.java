package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.UserType;
import com.example.demo.repository.UserTypeRepository;

@Service("usertypeserv")
public class UserTypeServImpl implements UserTypeService {

	@Autowired
	UserTypeRepository usertyperepo;
	
	@Override
	public UserType saveUserType(UserType usertype) {
	
		return usertyperepo.save(usertype);
	}

	@Override
	public List<UserType> getAllUserTypes() {
		
		return usertyperepo.findAll();
	}

}

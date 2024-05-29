package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;

@Service("userserv")
public class UserServImpl implements UserService {

	@Autowired
	UserRepository userrepo;
	
	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		return userrepo.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userrepo.findAll();
	}

}

package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.models.User;
import com.example.demo.service.UserService;

public class UserController {

	@Autowired
	UserService userserv;
	
	@PostMapping("/")
	public ResponseEntity<User> saveUser(User user)
	{
		 User usr = userserv.saveUser(user);
		 if(usr!=null)
			 return new ResponseEntity<User>(usr , HttpStatus.OK);
		 else
			 return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<User>> getAllUsers()
	{
		List<User> ulist = userserv.getAllUsers();
		if(ulist.size()>0)
		{
			return new ResponseEntity<List<User>>(ulist, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<User>>( HttpStatus.NO_CONTENT);
		}
	}
}

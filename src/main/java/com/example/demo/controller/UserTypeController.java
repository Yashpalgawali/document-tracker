package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.UserType;
import com.example.demo.service.UserTypeService;

@RestController
@RequestMapping("usertype")
public class UserTypeController {

	@Autowired
	UserTypeService usertypeserv;
	
	@PostMapping("/")
	public ResponseEntity<UserType> saveUserType(@RequestBody UserType usertype)
	{
		UserType utype = usertypeserv.saveUserType(usertype);
		if(utype!=null)
			return new ResponseEntity<UserType>(utype,HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserType>> getAllUserTypes()
	{
		List<UserType> ulist =  usertypeserv.getAllUserTypes();
		if(ulist.size()>0)
			return new ResponseEntity<List<UserType>>(ulist,HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}

package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.User;
import com.example.demo.models.Vendor;
import com.example.demo.service.UserService;
import com.example.demo.service.VendorService;

@RestController
@RequestMapping("vendor")
public class VendorController {

	@Autowired
	VendorService vendserv;
	
	@Autowired
	UserService userserv;
		
	@PostMapping("/")
	public ResponseEntity<Vendor> saveVendor(@RequestBody Vendor vendor)
	{
		Vendor vend = vendserv.saveVendor(vendor);
		if(vend!=null) {
			User user = new User();
			user.setEmail(vend.getVendor_email());
			user.setPassword(vendor.getPassword());
			user.setRole(vend.getVendor_type().getVendor_type());
			user.setUsername(vendor.getUsername());
			user.setEnabled(1);
			user.setVendor(vend);
			
			User savedUser = userserv.saveUser(user);
			if(savedUser!=null) {
				System.err.println("saved User  is "+savedUser.toString());
			}
			return new ResponseEntity<Vendor>(vend, HttpStatus.OK);
		}
		else
			return new ResponseEntity<Vendor>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Vendor>> getAllVendors()
	{
		List<Vendor> vlist = vendserv.getAllVendors();
		if(vlist.size()>0)
			return new ResponseEntity<List<Vendor>>(vlist , HttpStatus.OK);
		else
			return new ResponseEntity<List<Vendor>>( HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Vendor> getVendorById(@PathVariable("id") Integer id)
	{
		Vendor vendor = vendserv.getVendorById(id);
		if(vendor!=null)
			return new ResponseEntity<Vendor>(vendor , HttpStatus.OK);
		else
			return new ResponseEntity<Vendor>( HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/email/{email}")
	public ResponseEntity<Vendor> getVendorByEmail(@PathVariable("email") String email)
	{
		Vendor vendor = vendserv.getVendorByEmail(email);
		if(vendor!=null)
			return new ResponseEntity<Vendor>(vendor , HttpStatus.OK);
		else
			return new ResponseEntity<Vendor>( HttpStatus.NO_CONTENT);
	}
}
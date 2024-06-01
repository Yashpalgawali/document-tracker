package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.VendorType;
import com.example.demo.service.VendorTypeService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("vendortype")
public class VendorTypeController {

	@Autowired
	VendorTypeService vendortypeserv;
	
	@PostMapping("/")
	public ResponseEntity<VendorType> saveVendorType(@RequestBody VendorType vendortype)
	{
		System.err.println(vendortype.toString());
		VendorType vtype = vendortypeserv.saveVendorType(vendortype);
		if(vtype!=null)
			return new ResponseEntity<VendorType>(vtype , HttpStatus.OK);
		else
			return new ResponseEntity<VendorType>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<VendorType>> getAllVendorTypes()
	{
		List<VendorType> vlist = vendortypeserv.getAllVendorTypes();
		if(vlist.size()>0)
			return new ResponseEntity<List<VendorType>>(vlist ,HttpStatus.OK);
		else
			return new ResponseEntity<List<VendorType>>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<VendorType> getAllVendorTypeById(@PathVariable("id") Integer vid)
	{
		VendorType vtype = vendortypeserv.getVendorType(vid);
		if(vtype!=null)
			return new ResponseEntity<VendorType>(vtype , HttpStatus.OK);
		else
			return new ResponseEntity<VendorType>(HttpStatus.NO_CONTENT);
	}
}

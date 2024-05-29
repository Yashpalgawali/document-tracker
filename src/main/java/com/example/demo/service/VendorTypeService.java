package com.example.demo.service;

import java.util.List;

import com.example.demo.models.VendorType;

public interface VendorTypeService {

	public VendorType saveVendorType(VendorType vtype);
	
	public List<VendorType> getAllVendorTypes();
	
	public VendorType getVendorType(Integer vid);
}

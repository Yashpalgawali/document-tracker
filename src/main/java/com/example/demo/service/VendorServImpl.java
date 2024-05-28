package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Vendor;
import com.example.demo.repository.VendorRepository;

@Service("vendorserv")
public class VendorServImpl implements VendorService {

	@Autowired
	VendorRepository vendorrepo;
	
	@Override
	public Vendor saveVendor(Vendor vendor) {
		// TODO Auto-generated method stub
		return vendorrepo.save(vendor);
	}

	@Override
	public List<Vendor> getAllVendors() {
		// TODO Auto-generated method stub
		return vendorrepo.findAll();
	}

	@Override
	public Vendor getVendorById(Integer vendorid) {
		// TODO Auto-generated method stub
		Optional<Vendor> vendor = vendorrepo.findById(vendorid);
		if(vendor!=null) {
			return vendor.get();
		}
		else
			return vendor.orElseThrow();
	}

	@Override
	public Vendor getVendorByEmail(String email) {
		// TODO Auto-generated method stub
		return vendorrepo.findByVendor_email(email);
	}

}

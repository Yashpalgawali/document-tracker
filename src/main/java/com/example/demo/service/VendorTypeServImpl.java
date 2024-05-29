package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.VendorType;
import com.example.demo.repository.VendorTypeRepository;

@Service("vendortypeserv")
public class VendorTypeServImpl implements VendorTypeService {

	@Autowired
	VendorTypeRepository vendortyperepo;
	
	@Override
	public VendorType saveVendorType(VendorType vtype) {
		// TODO Auto-generated method stub
		return vendortyperepo.save(vtype);
	}

	@Override
	public List<VendorType> getAllVendorTypes() {
		// TODO Auto-generated method stub
		return vendortyperepo.findAll();
	}

	@Override
	public VendorType getVendorType(Integer vid) {
		// TODO Auto-generated method stub
		return vendortyperepo.findById(vid).get();
	}

}

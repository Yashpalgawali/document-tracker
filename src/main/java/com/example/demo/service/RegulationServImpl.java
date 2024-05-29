package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Regulation;
import com.example.demo.repository.RegulationRepository;

@Service("regulationserv")
public class RegulationServImpl implements RegulationService {

	@Autowired
	RegulationRepository regulationrepo;
	
	@Override
	public Regulation saveRegulation(Regulation regulation) {
		// TODO Auto-generated method stub
		return regulationrepo.save(regulation);
	}

	@Override
	public List<Regulation> getAllRegulations() {
		// TODO Auto-generated method stub
		return regulationrepo.getAllRegulations();
	}

	@Override
	public Regulation getRegulationById(Integer id) {
		// TODO Auto-generated method stub
		Optional<Regulation> reg = regulationrepo.findById(id);
		if(reg.isPresent()) {
			return reg.get();
		}
		else 
			return null;
	}

	@Override
	public List<Regulation> getRegulationByVendorId(Integer vendor_id) {

		return regulationrepo.getAllRegulationsByVendorId(vendor_id);
	}

}

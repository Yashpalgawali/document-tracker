package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Regulation;

@Repository("regulationrepo")
public interface RegulationRepository extends JpaRepository<Regulation, Integer> {

	@Query("SELECT r FROM Regulation r ")
	public List<Regulation> getAllRegulations();
	
	
	@Query("SELECT r FROM Regulation r JOIN r.vendor WHERE r.vendor.vendor_id=:vendor_id")
	public List<Regulation> getAllRegulationsByVendorId(Integer vendor_id);
}

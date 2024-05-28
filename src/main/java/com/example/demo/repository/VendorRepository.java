package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Vendor;

@Repository("vendorrepo")
public interface VendorRepository extends JpaRepository<Vendor, Integer> {

	@Query("SELECT v FROM Vendor v WHERE v.vendor_email=:vendor_email")
	public Vendor findByVendor_email(String vendor_email);
}

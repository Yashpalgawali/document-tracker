package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.VendorType;

@Repository("vendortyperepo")
public interface VendorTypeRepository extends JpaRepository<VendorType, Integer> {

}

package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "vendor_type_seq",initialValue = 1 , allocationSize = 1)
@Table(name="tbl_vendor_type")
public class VendorType {

	@Id
	@GeneratedValue(generator = "vendor_type_seq", strategy = GenerationType.AUTO)
	private Integer vendor_type_id;
	
	private String vendor_type;
}

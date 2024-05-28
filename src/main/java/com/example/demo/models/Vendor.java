package com.example.demo.models;

import javax.persistence.Column;
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
@Table(name="tbl_vendor")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "vendor_seq",allocationSize = 1,initialValue = 1)
public class Vendor {

	@Id
	@GeneratedValue(generator = "vendor_seq",strategy = GenerationType.AUTO)
	private Integer vendor_id;
	
	private String vendor_name;
	
	@Column(unique = true)
	private String vendor_email;
	
	private String vendor_contact;
}

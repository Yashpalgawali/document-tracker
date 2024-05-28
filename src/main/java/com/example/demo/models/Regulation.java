package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tbl_regulation")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "regulation_seq")
public class Regulation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "regulation_seq")
	private Integer regulation_id;
	
	private String regulation_name;
	
	private String regulation_description;
	
	private String regulation_frequency;
	
	private String regulation_issued_date;
	
	private String file_name;
	
	private String file_path;
	
	@ManyToOne
	@JoinColumn(name = "vendor_id",nullable = false)
	private Vendor vendor;
}

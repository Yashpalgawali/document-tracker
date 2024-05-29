package com.example.demo.models;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "user_seq", allocationSize = 1, initialValue = 1)
@Table(name="tbl_user")
public class User {

	@Id
	@GeneratedValue(generator = "user_seq",strategy = GenerationType.AUTO)
	private Integer user_id;
	
	@Column(unique = true)
	private String username;
	
	@Column(unique = true)
	private String email;
	
	private String password;
	
	private Integer enabled;
	
	private String role;
	
	@OneToOne
	@JoinColumn(name= "vendor_id")
	private Vendor vendor; 
}

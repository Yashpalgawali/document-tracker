package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.models.Regulation;
import com.example.demo.service.RegulationService;

@RestController
@RequestMapping("regulation")
@CrossOrigin("*")
public class RegulationController {

	@Autowired
	RegulationService regulationserv;
	
	@Value("${spring.application.name}")
    private String contextPath;
	
	@Value("${upload.dir}")
    private String uploadPath;

	@PostMapping("/")
	 public ResponseEntity<Regulation> saveRegulation(
	            @RequestPart("regulation_name") String regulation_name,
	            @RequestPart("regulation_description") String regulation_description,
	            @RequestPart("regulation_frequency") String regulation_frequency,
	            @RequestPart("regulation_issued_date") String regulation_issued_date,
	            @RequestPart(value = "file", required = false) MultipartFile file) {
		
	        Regulation reg = new Regulation();
	        reg.setRegulation_name(regulation_name);
	        reg.setRegulation_description(regulation_description);
	        reg.setRegulation_frequency(regulation_frequency);
	        reg.setRegulation_issued_date(regulation_issued_date);
	        reg.setFile(file);
	        
	        System.err.println("inside saveregulation() \n "+reg.toString());
	        
	        if (file != null) {
	        	File uploadDirectory = new File(uploadPath);
	        	if(!uploadDirectory.exists())
	        	{
	        		 boolean created = uploadDirectory.mkdirs();
	        		 if(created)
	        		 {
	        			 File vendorDir = new File(uploadPath+File.separator+"Quality"+File.separator+1);
	        			 if(!vendorDir.exists())
	        			 {
	        				 boolean cr = vendorDir.mkdirs();
	        				 if(cr)
	        				 {
	        					 Path path = Paths.get(vendorDir.getAbsolutePath());
	        					 System.err.println("New Vendor Deirectory created \n Absolute Path of vendor Directory is "+path);
	        				 }
	        			 }
	        			 else {
	        				 System.out.println("vendor directory alredy exists \n");
	        				 Path path = Paths.get(vendorDir.getAbsolutePath());
        					 System.err.println(" Absolute Path of vendor Directory is "+path);
	        			 }
	        		 }
	        	}
	        	else {
	        		 System.err.println("Already exists \n");
	        		
        			 File vendorDir = new File(uploadPath+File.separator+"Quality"+File.separator+1);
        			 if(!vendorDir.exists())
        			 {
        				 boolean cr = vendorDir.mkdirs();
        				 if(cr)
        				 {
        					 Path path = Paths.get(vendorDir.getAbsolutePath());
        					 System.err.println("New Vendor Deirectory created \n Absolute Path of vendor Directory is "+path);
        				 }
        			 }
        			 else {
        				 System.out.println("Inside else block the Vendor directory alredy exists \n");
        				 Path path = Paths.get(vendorDir.getAbsolutePath());
        				 System.err.println(" Absolute Path of vendor Directory is "+path);
        				 
        				 String filename = file.getOriginalFilename();
        				 try {
        					 Path filePath = Paths.get(vendorDir.getAbsolutePath(), filename);
        					// Save the file to the specified directory
        					   Files.copy(file.getInputStream(), filePath);

							
						} catch (IOException e) {
							
							e.printStackTrace();
						}
        			 }
	        		
	        	}
	        }
			Regulation regulation = regulationserv.saveRegulation(reg);
			if(reg!=null)
				return new ResponseEntity<Regulation>( regulation ,HttpStatus.OK);
			else
				return new ResponseEntity<Regulation>( HttpStatus.INTERNAL_SERVER_ERROR);	 
	    }
	
	
//	@PostMapping("/")
//	public ResponseEntity<Regulation> saveRegulation(@RequestBody Regulation regulation, HttpSession sess)
//	{
////		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
////                .replacePath(contextPath)
////                .build()
////                .toUri();
////		
////		sess.setAttribute("uri", uri);
//		String file_name =  regulation.getFile().getOriginalFilename();
//		System.err.println("File name is "+file_name+"\n data is "+regulation.toString());
//		
//		 File uploadDirectory = new File(uploadPath);
//	        if (!uploadDirectory.exists()) {
//	            boolean created = uploadDirectory.mkdirs();
//	            if (created) {
//	            	File vendorDir = new File(uploadPath+File.separator+"Quality");
//	            	if(!vendorDir.exists())
//	            	{
//	            		boolean cr = vendorDir.mkdirs();
//	            		if(cr) {
//	            			System.out.println("\n New dir created at "+vendorDir.getAbsolutePath());
//	            		}
//	            		else {
//	            			System.out.println("\n New dir ALREADY EXISTS at "+vendorDir.getAbsolutePath());
//	            		}
//	            	}
//	            	System.out.println("Uploads directory created successfully at " + uploadDirectory.getAbsolutePath());
//	            } else {
//	                System.err.println("Failed to create uploads directory at " + uploadDirectory.getAbsolutePath());
//	            }
//	        } else {
//	            System.out.println("Uploads directory already exists at " + uploadDirectory.getAbsolutePath());
//	        }
//		Regulation reg = regulationserv.saveRegulation(regulation);
//		if(reg!=null)
//			return new ResponseEntity<Regulation>( reg ,HttpStatus.OK);
//		else
//			return new ResponseEntity<Regulation>( HttpStatus.INTERNAL_SERVER_ERROR);
//	}
	
	@GetMapping("/")
	public ResponseEntity<List<Regulation>> getAllRegulations(HttpSession sess)
	{
		List<Regulation> rlist = regulationserv.getAllRegulations();
		if(rlist.size()>0)
			return new ResponseEntity<List<Regulation>>(rlist, HttpStatus.OK);
		else
			return new ResponseEntity<List<Regulation>>( HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<List<Regulation>> getRegulationByVendorId(@PathVariable("id") Integer id)
	{
		List<Regulation> rlist = regulationserv.getRegulationByVendorId(id);
		if(rlist.size()>0) {
			return new ResponseEntity<List<Regulation>>(rlist, HttpStatus.OK);
		}
		else
			return new ResponseEntity<List<Regulation>>( HttpStatus.NO_CONTENT);
	}
}

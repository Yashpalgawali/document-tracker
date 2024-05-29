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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.models.Regulation;
import com.example.demo.service.RegulationService;

@RestController
@RequestMapping("regulation")
public class RegulationController {

	@Autowired
	RegulationService regulationserv;
	
	@Value("${spring.application.name}")
    private String contextPath;
	
	@Value("${upload.dir}")
    private String uploadPath;
	

	@PostMapping("/")
	public ResponseEntity<Regulation> saveRegulation(@RequestBody Regulation regulation, HttpSession sess)
	{
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
//                .replacePath(contextPath)
//                .build()
//                .toUri();
//		
//		sess.setAttribute("uri", uri);
//		//String file_name =  regulation.getFile().getOriginalFilename();
//		System.err.println("File name is \n data is "+regulation.toString());
		
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
		Regulation reg = regulationserv.saveRegulation(regulation);
		if(reg!=null)
			return new ResponseEntity<Regulation>( reg ,HttpStatus.OK);
		else
			return new ResponseEntity<Regulation>( HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
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
		if(rlist.size()>0)
		{
			rlist.stream().forEach(e->System.err.println(e.toString()));
			return new ResponseEntity<List<Regulation>>(rlist, HttpStatus.OK);
		}
		else
			return new ResponseEntity<List<Regulation>>( HttpStatus.NO_CONTENT);
	}
}

package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

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
import com.example.demo.models.Vendor;
import com.example.demo.service.RegulationService;
import com.example.demo.service.VendorService;

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

	@Autowired
	VendorService vendserv;
	
	@PostMapping("/")
	 public ResponseEntity<Regulation> saveRegulation(
	            @RequestPart("regulation_name") String regulation_name,
	            @RequestPart("regulation_description") String regulation_description,
	            @RequestPart("regulation_frequency") String regulation_frequency,
	            @RequestPart("regulation_issued_date") String regulation_issued_date,
	            @RequestPart(value = "file", required = false) MultipartFile file) {
		
	       String filename = file.getOriginalFilename();
	       String filepath = "";  
	       Regulation reg = new Regulation();
	       Regulation regulation = null;
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
        				 						 
        				 try {
        					
        					 File f = new File(vendorDir.getAbsolutePath()+File.separator +filename); 
        					  if(f.exists() ) {
        						  String extension =  filename.substring(filename.lastIndexOf("."), filename.length()) ;
        						
        	        			  filename =  filename.substring(0, filename.lastIndexOf("."))+"-1"+extension  ; 
        						  //f = new File(vendorDir.getAbsolutePath()+filename);
        					  }
        					  else {
        						  System.out.println("THIS IS A NEW FILE \n");
        					  }
        					 
        					 Path filePath = Paths.get(vendorDir.getAbsolutePath(), filename);
        					 filepath= vendorDir.getAbsolutePath()+File.separator+filename;
        				        reg.setRegulation_name(regulation_name);
        				        reg.setRegulation_description(regulation_description);
        				        reg.setRegulation_frequency(regulation_frequency);
        				        reg.setRegulation_issued_date(regulation_issued_date);
        				        reg.setFile_name(filename);
        				        reg.setFile_path(filepath);
        				        
        				        Vendor vendor = vendserv.getVendorById(1);
        				        reg.setVendor(vendor);
        				        regulation = regulationserv.saveRegulation(reg);
        					// Save the file to the specified directory
        					   Files.copy(file.getInputStream(), filePath);
        					   // Delete the temporary file
        			            deleteTempFile(file.getInputStream());
							
						} catch (IOException e) {
							
							e.printStackTrace();
						}
        				 
        			 }
	        		
	        	}
	        }
	      
	        
			if(regulation!=null)
				return new ResponseEntity<Regulation>( regulation ,HttpStatus.OK);
			else
				return new ResponseEntity<Regulation>( HttpStatus.INTERNAL_SERVER_ERROR);	 
	    }



	private void deleteTempFile(InputStream ipstream){
			try {
				ipstream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	

	
	@GetMapping("/")
	public ResponseEntity<List<Regulation>> getAllRegulations(HttpSession sess) throws Exception
	{
		//Files.list(Paths.get("uploads")).forEach((System.out::println));
		
//		Predicate<? super Path> predicate = path -> String.valueOf(path).contains(".java");
//		Files.walk(Paths.get("."), 10).filter(predicate ).forEach(System.out::println);
		
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

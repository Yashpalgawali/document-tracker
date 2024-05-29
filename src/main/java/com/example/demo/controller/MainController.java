package com.example.demo.controller;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

	
	@GetMapping("/")
	public String index()
	{
		return "Main controller \n URI in session is ";
	}
}
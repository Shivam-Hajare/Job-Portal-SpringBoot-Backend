package com.app.Job_Portal.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.app.Job_Portal.dto.RecruiterSignUpDto;
import com.app.Job_Portal.service.SignUpService;

@RestController
@RequestMapping("/signup")
@CrossOrigin(origins = "*")
public class SignUpController {

	@Autowired
	private SignUpService signUpService;
	
	@PostMapping("/newRegistration")
	public ResponseEntity<String> registrationOfNewRecruiter(@RequestBody @Valid RecruiterSignUpDto recruiterDto)
	{
		
		
	
		return new ResponseEntity<String>(signUpService.registrationOfRecruiter(recruiterDto),HttpStatus.OK);
		
	}
	
	
	
	
}

package com.app.Job_Portal.controller;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Job_Portal.dto.RecruiterSignUpDto;
import com.app.Job_Portal.dto.SignInRequestDto;
import com.app.Job_Portal.dto.SignInResponseDto;
import com.app.Job_Portal.service.SignInService;

@RestController
@RequestMapping("/signin")
public class SignInController {

	@Autowired
	private SignInService signInService;
	
	@PostMapping("/User")
	public SignInResponseDto authenticationOfUser(@RequestBody SignInRequestDto signInReqDto)
	{
		return signInService.authenticationOfUser(signInReqDto);
	}
	
	
	/*
	 * 
	 * @PostMapping("/newRegistration")
	public ResponseEntity<String> registrationOfNewRcruiter(@RequestBody RecruiterSignUpDto recruiterDto)
	{
		
		return new ResponseEntity<String>(signUpService.registrationOfRecruiter(recruiterDto),HttpStatus.OK);
	}
	 */
	
	
}

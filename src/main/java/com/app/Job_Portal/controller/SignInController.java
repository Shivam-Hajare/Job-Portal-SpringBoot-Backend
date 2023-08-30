package com.app.Job_Portal.controller;

import javax.validation.Valid;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "*")
public class SignInController {

	@Autowired
	private SignInService signInService;
	
	@PostMapping("/User")
	public SignInResponseDto authenticationOfUser(@RequestBody @Valid SignInRequestDto signInReqDto)
	{
		return signInService.authenticationOfUser(signInReqDto);
	}
	
	
	
	
	
}

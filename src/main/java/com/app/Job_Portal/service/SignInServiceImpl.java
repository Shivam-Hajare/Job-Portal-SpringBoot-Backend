package com.app.Job_Portal.service;



import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.app.Job_Portal.dto.SignInRequestDto;
import com.app.Job_Portal.dto.SignInResponseDto;

import com.app.Job_Portal.entities.User;
import com.app.Job_Portal.exceptions.ResourceNotFoundException;
import com.app.Job_Portal.repository.UserRepository;


@Service
@Transactional
public class SignInServiceImpl implements SignInService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
    private ModelMapper mapper;
	
	public  SignInResponseDto authenticationOfUser(SignInRequestDto signInDto)
	{
		User specificUser=userRepo.findByEmail(signInDto.getUserName()).orElseThrow(()-> new ResourceNotFoundException("Invalid Username"));
		
		String cryptPassword=new BCryptPasswordEncoder().encode(signInDto.getPassword());
		
		if(!(specificUser.getEmail().equals(signInDto.getUserName())&&specificUser.getPassword().equals(cryptPassword)))
		{
			throw new RuntimeException("Password is not correct");
		}
		
		SignInResponseDto userHolder=mapper.map(specificUser, SignInResponseDto.class);
		
		
		userHolder.setEmail(specificUser.getEmail());
		userHolder.setUserId(specificUser.getUserId());
		if(specificUser.getRole().equals("ROLE_RECRUITER"))
			{
				userHolder.setRecruiterId(specificUser.getRecruiter().getRecruiterId());
			}
			if(specificUser.getRole().equals("ROLE_JOBSEEKER"))
			{
				userHolder.setJobSeekerId(specificUser.getJobSeeker().getJobSeekerId());
			}
			
		
			return userHolder;
		
	
	
	
	
	
	}
	
	
	
}

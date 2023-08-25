package com.app.Job_Portal.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.Job_Portal.dto.RecruiterSignUpDto;
import com.app.Job_Portal.entities.Recruiter;
import com.app.Job_Portal.entities.User;
import com.app.Job_Portal.repository.RecruiterRepository;
import com.app.Job_Portal.repository.UserRepository;


@Service
@Transactional
public class SignUpServiceImpl implements SignUpService {

	@Autowired
	private RecruiterRepository recruiterRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	public String registrationOfRecruiter(RecruiterSignUpDto recruiterDto)
	{
		Recruiter newRecruiter=new Recruiter();
		
		newRecruiter.setEmail(recruiterDto.getEmail());
		newRecruiter.setFirstName(recruiterDto.getFirstName());
		newRecruiter.setLastName(recruiterDto.getLastName());
		newRecruiter.setRecruiterBio(recruiterDto.getRecruiterBio());
		newRecruiter.setCompanyName(recruiterDto.getCompanyName());
		newRecruiter.setPhoneNo(recruiterDto.getPhoneNo());
		
		recruiterRepo.save(newRecruiter);
		
		User newUser=new User();
		
		newUser.setEmail(recruiterDto.getEmail());
		newUser.setPassword(recruiterDto.getPassword());
		newUser.setRecruiter(newRecruiter);
		newUser.setRole(recruiterDto.getRole());
		
		userRepo.save(newUser);
		
		return "Registration Completed Successfully"
;	}
}

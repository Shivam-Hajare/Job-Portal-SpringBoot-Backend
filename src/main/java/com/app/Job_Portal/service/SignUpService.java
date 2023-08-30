package com.app.Job_Portal.service;

import com.app.Job_Portal.dto.JobSeekerRequestDto;
import com.app.Job_Portal.dto.JobseekerSignUpRequestDto;
import com.app.Job_Portal.dto.RecruiterSignUpDto;

public interface SignUpService {

	
	String registrationOfRecruiter(RecruiterSignUpDto recruiterDto);

    String registrationOfJobseeker(JobseekerSignUpRequestDto seekerDto);
}

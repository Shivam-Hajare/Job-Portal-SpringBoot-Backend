package com.app.Job_Portal.service;

import java.util.List;

import com.app.Job_Portal.dto.JobSeekerResponseDto;
import com.app.Job_Portal.dto.RecruiterDto;

public interface AdminService {

	List<JobSeekerResponseDto> getAllJobseekers();

	List<RecruiterDto> getAllRecruiters();
	
	String deleteJobSeekerProfile(Long jobSeekerId);
	
	String deleteRecruiterProfile(Long recruiterId);
}

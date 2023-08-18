package com.app.Job_Portal.service;

import java.util.List;

import com.app.Job_Portal.dto.JobListDto;
import com.app.Job_Portal.entities.Job;

public interface RecruiterService {

	public List<JobListDto> getAllJobsPosted(Long recruiterId);
	
}

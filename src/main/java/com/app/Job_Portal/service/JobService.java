package com.app.Job_Portal.service;

import java.util.List;

import com.app.Job_Portal.dto.JobListDto;
import com.app.Job_Portal.dto.PostJobRequestDto;

public interface JobService {
	List<JobListDto> getAllJobs();

	String postJob(PostJobRequestDto postJobRequestDto);
}

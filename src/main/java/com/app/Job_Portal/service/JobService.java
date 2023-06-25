package com.app.Job_Portal.service;

import java.util.List;

import com.app.Job_Portal.dto.JobListDto;

public interface JobService {
	List<JobListDto> getAllJobs();
}

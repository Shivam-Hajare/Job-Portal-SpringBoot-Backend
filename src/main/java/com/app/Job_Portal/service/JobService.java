package com.app.Job_Portal.service;

import java.util.List;

import com.app.Job_Portal.dto.JobApplicationsListDto;
import com.app.Job_Portal.dto.JobListDto;

public interface JobService {
	List<JobListDto> getAllJobs();
	List<JobApplicationsListDto> getListOfJobApplications(Long jobId);
	List<JobListDto>JobsWithTitle(String jobTiltle);
}

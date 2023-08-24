package com.app.Job_Portal.service;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.app.Job_Portal.dto.JobApplicationsListDto;
import com.app.Job_Portal.dto.JobListDto;
import com.app.Job_Portal.dto.PostJobRequestDto;
import com.app.Job_Portal.dto.UpdateJobRequestDto;
import com.app.Job_Portal.entities.Job;

public interface JobService {
	List<JobListDto> getAllJobs();
	List<JobApplicationsListDto> getListOfJobApplications(Long jobId);
	//List<JobListDto>webJobs(String jobTiltle);
}

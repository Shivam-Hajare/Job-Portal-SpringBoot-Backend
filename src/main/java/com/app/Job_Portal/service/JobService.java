package com.app.Job_Portal.service;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.app.Job_Portal.dto.JobListDto;
import com.app.Job_Portal.dto.PostJobRequestDto;
import com.app.Job_Portal.dto.UpdateJobRequestDto;

public interface JobService {
	List<JobListDto> getAllJobs();

	String postJob(PostJobRequestDto postJobRequestDto);

	Map<String, Boolean> deleteJob(Long jobId);

	String updateJob(Long jobId, UpdateJobRequestDto updateJobRequestDto);

	String updateApplicationStatus(Long jobId, Long jobSeekerId, @Valid String jobStatus);
}

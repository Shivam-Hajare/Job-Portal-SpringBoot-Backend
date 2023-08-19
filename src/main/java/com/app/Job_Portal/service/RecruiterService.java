package com.app.Job_Portal.service;

import java.util.List;
import java.util.Map;

import com.app.Job_Portal.dto.JobApplicationsListDto;
import com.app.Job_Portal.dto.JobListDto;
import com.app.Job_Portal.dto.PostJobRequestDto;
import com.app.Job_Portal.dto.UpdateJobRequestDto;

public interface RecruiterService {

	public List<JobListDto> getAllJobsPosted(Long recruiterId);
	
	String postJob(PostJobRequestDto postJobRequestDto);
	
	Map<String, Object> deleteJobByRecruiter(Long jobId, Long recruiterId);
	public String updateJobByRecruiter(Long jobId, UpdateJobRequestDto updateJobRequestDto, Long recruiterId);
	
	public String updateApplicationStatusByRecruiter(Long jobId, Long jobSeekerId, String jobStatus, Long recruiterId);
	
	 public List<JobApplicationsListDto> getListOfJobApplications(Long jobId, Long loggedInRecruiterId);
}

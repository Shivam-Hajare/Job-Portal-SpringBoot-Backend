package com.app.Job_Portal.service;

import java.util.List;
import java.util.Map;

import com.app.Job_Portal.dto.JobApplicationsListDto;
import com.app.Job_Portal.dto.JobListDto;
import com.app.Job_Portal.dto.JobStatusDto;
import com.app.Job_Portal.dto.PostJobRequestDto;
import com.app.Job_Portal.dto.RecruiterRequestDto;
import com.app.Job_Portal.dto.UpdateJobRequestDto;
import com.app.Job_Portal.entities.Recruiter;


public interface RecruiterService {

	 List<JobListDto> getAllJobsPosted(Long recruiterId);
	
	String postJob(PostJobRequestDto postJobRequestDto);
	
	Map<String, Object> deleteJobByRecruiter(Long jobId, Long recruiterId);
	
	 String updateJobByRecruiter(Long jobId, UpdateJobRequestDto updateJobRequestDto, Long recruiterId);
	
	
	  String updateApplicationStatusByRecruiter(Long jobId, Long jobSeekerId, JobStatusDto jobStatus,
				Long recruiterId);
	
	  List<JobApplicationsListDto> getListOfJobApplications(Long jobId, Long loggedInRecruiterId);

	  String updateProfile(RecruiterRequestDto recruiterDto, Long recuiterId);
	  
	  RecruiterRequestDto recrutierById(Long recuiterId );
	  
	  
}

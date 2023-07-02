package com.app.Job_Portal.service;


import com.app.Job_Portal.dto.JobListDto;
import com.app.Job_Portal.entities.JobListing;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface JobSeekerService {

    List<JobListDto> getAllJobs();

    List<JobListDto> getJobsWithGivenType(String jobType);

    List<JobListDto> getJobsWithGivenTitle(String title);

    List<JobListDto> getappliedJobs(Long jobseekerId);
}

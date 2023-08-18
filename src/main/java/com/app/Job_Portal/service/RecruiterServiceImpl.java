package com.app.Job_Portal.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.Job_Portal.dto.JobListDto;
import com.app.Job_Portal.entities.Recruiter;
import com.app.Job_Portal.exceptions.ResourceNotFoundException;
import com.app.Job_Portal.repository.RecruiterRepository;
@Service
@Transactional
public class RecruiterServiceImpl implements RecruiterService{

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private RecruiterRepository recruiterRepo;
	
	public List<JobListDto> getAllJobsPosted(Long recruiterId) {
		Recruiter recruiter= recruiterRepo.findById(recruiterId).orElseThrow(
				() -> new ResourceNotFoundException("recruiter not found with ID: " + recruiterId));;
		
				List<JobListDto> jobList=new ArrayList<JobListDto>();
				
				recruiter.getJobListings().forEach(job->{
					JobListDto newJob=mapper.map(job, JobListDto.class);
					newJob.setRecruiterName(recruiter.getFirstName()+" "+recruiter.getLastName());
					jobList.add(newJob);
				});
		return jobList;
	}

}

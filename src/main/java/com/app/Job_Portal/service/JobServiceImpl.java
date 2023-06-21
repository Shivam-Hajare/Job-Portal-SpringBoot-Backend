package com.app.Job_Portal.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.Job_Portal.entities.JobListing;
import com.app.Job_Portal.repository.JobRepository;

@Service
@Transactional
public class JobServiceImpl implements JobService {
  
	@Autowired
	private JobRepository JobRepo;

	@Override
	public List<JobListing> getAllJobs() {
		return JobRepo.findAll();
	}
	
	
}

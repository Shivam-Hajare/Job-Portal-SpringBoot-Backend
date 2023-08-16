package com.app.Job_Portal.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.Job_Portal.dto.JobListDto;
import com.app.Job_Portal.entities.Job;
import com.app.Job_Portal.repository.JobRepository;

@Service
@Transactional
public class JobServiceImpl implements JobService {
  
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private JobRepository JobRepo;

	@Override
	public List<JobListDto> getAllJobs() {
		List<Job> list= JobRepo.findAll();
		
		return list.stream()
				.map(l -> {
					JobListDto joblistDto =	mapper.map(l, JobListDto.class);
					
					joblistDto.setRecruiterName(l.getPostedBy().getFirstName());
					return joblistDto;
				})
				.collect(Collectors.toList());
	}
	
	
}

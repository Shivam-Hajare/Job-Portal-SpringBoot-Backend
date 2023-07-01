package com.app.Job_Portal.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.Job_Portal.dto.JobListDto;
import com.app.Job_Portal.dto.PostJobRequestDto;
import com.app.Job_Portal.entities.JobListing;
import com.app.Job_Portal.repository.JobRepository;
import com.app.Job_Portal.repository.RecruiterRepository;

@Service
@Transactional
public class JobServiceImpl implements JobService {
  
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private JobRepository JobRepo;
	
	@Autowired
	private RecruiterRepository recruiterRepository;

	@Override
	public List<JobListDto> getAllJobs() {
		List<JobListing> list= JobRepo.findAll();
		
		return list.stream()
				.map(l -> {
					JobListDto joblistDto =	mapper.map(l, JobListDto.class);
					
					joblistDto.setRecruiterName(l.getPostedBy().getFirstName());
					return joblistDto;
				})
				.collect(Collectors.toList());
	}

	@Override
	public String postJob(PostJobRequestDto postJobRequestDto) {
		if(recruiterRepository.existsById(postJobRequestDto.getRecruiterId()))
		{
			JobRepo.save(mapper.map(postJobRequestDto, JobListing.class));
			return "job added";
		}
		return "not added job!!";
	}
	
	
}

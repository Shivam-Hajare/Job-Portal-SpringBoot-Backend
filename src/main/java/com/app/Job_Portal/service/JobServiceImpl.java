package com.app.Job_Portal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.Job_Portal.dto.JobApplicationsListDto;
import com.app.Job_Portal.dto.JobListDto;
import com.app.Job_Portal.dto.SkillDto;
import com.app.Job_Portal.dto.UpdateJobRequestDto;
import com.app.Job_Portal.entities.Job;
import com.app.Job_Portal.entities.JobApplication;
import com.app.Job_Portal.entities.JobSeeker;
import com.app.Job_Portal.entities.Skill;
import com.app.Job_Portal.entities.Status;
import com.app.Job_Portal.exceptions.ResourceNotFoundException;
import com.app.Job_Portal.repository.JobApplicationRepository;
import com.app.Job_Portal.repository.JobRepository;
import com.app.Job_Portal.repository.JobSeekerRepository;
import com.app.Job_Portal.repository.RecruiterRepository;
import com.app.Job_Portal.repository.SkillRepository;

@Service
@Transactional
public class JobServiceImpl implements JobService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private JobRepository jobRepo;

	@Autowired
	private RecruiterRepository recruiterRepository;

	@Autowired
	private SkillRepository skillRepository;

	@Autowired
	private JobApplicationRepository jobApplicationRepo;
	
	@Autowired
	private JobSeekerRepository jobSeekerRepo;
	
	

	@Override
	public List<JobListDto> getAllJobs() {

		List<Job> list= jobRepo.findAll();
		
		return list.stream()
				.map(l -> {
					JobListDto joblistDto =	mapper.map(l, JobListDto.class);
					
					joblistDto.setRecruiterName(l.getPostedBy().getFirstName());
					return joblistDto;
				})
				.collect(Collectors.toList());
	}	



	@Override
	public List<JobApplicationsListDto> getListOfJobApplications(Long jobId) {
		//to save result and return
		List<JobApplicationsListDto> jobAppListDto = new ArrayList<>();
		
		//Taking job on which list of applications are finding
		Job job = jobRepo.findById(jobId)
				.orElseThrow(() -> new ResourceNotFoundException("job not found with ID: " + jobId));
		
		List<JobApplication> listOfApplications =job.getApplications();
		
		listOfApplications.forEach(jobApp -> {
			//mapping attributes
		    JobApplicationsListDto dto = mapper.map(jobApp, JobApplicationsListDto.class);
		    dto.setJobSeekerId(jobApp.getJobSeeker().getJobSeekerId());
		    dto.setFirstName(jobApp.getJobSeeker().getFirstName());
		    dto.setLastName(jobApp.getJobSeeker().getLastName());
		    
		    Long jobSeekerId = jobApp.getJobSeeker().getJobSeekerId();

	        // Fetch skills using skillRepository's custom query method
	        List<Skill> jobSeekerSkills = skillRepository.findAllSkillsByJobSeekerId(jobSeekerId);

	        // Create a list to hold SkillDto instances
	        List<SkillDto> skillDtos = new ArrayList<>();

	        // Convert Skill entities to SkillDto instances
	        for (Skill skill : jobSeekerSkills) {
	            SkillDto skillDto = new SkillDto(skill.getSkillId(), skill.getName(), skill.getDescription());
	            skillDtos.add(skillDto);
	        }
	        
		    dto.setSkills(skillDtos);
		    
		    jobAppListDto.add(dto);
		});
		
		return jobAppListDto;
	}

//	public List<JobListDto>webJobs(String jobTitle)
//	{
//		List<Job> jobLists = jobRepo.findAll();
//		
//		List<Job> webjobs=jobLists.stream().filter(t->t.getJobTitle().contains(jobTitle)).collect(Collectors.toList());
//	
//		List<JobListDto> listOfJobListDtos = new ArrayList<>();
//		
//	    webjobs.forEach(wj->{
//		JobListDto allwebjobs=mapper.map(wj, JobListDto.class);
////		allwebjobs.setJobId(wj.getJobId());
//		allwebjobs.setJobTitle(wj.getJobTitle());
//		allwebjobs.setJobDescription(wj.getJobDescription());
//		allwebjobs.setPostedDate(wj.getPostedDate());
//		allwebjobs.setDeadLineDate(wj.getDeadLineDate());
//		allwebjobs.setNoOfJobPositions(wj.getNoOfJobPositions());
//		allwebjobs.setSalary(wj.getSalary());
//		allwebjobs.setJobType(wj.getJobType());
//		String recruiterName=allwebjobs.getRecruiterName();
//		allwebjobs.setRecruiterName(recruiterName);
////		allwebjobs.setSkillsForJob(wj.getSkills());
////		List<SkillDto>skills=skillRepository.
//		System.out.println(wj.getSkills().size()+ " helloo");
//		wj.getSkills().forEach(skill -> {
//		allwebjobs.getSkillsForJob().add(mapper.map(skill, SkillDto.class));	
//		});
//		listOfJobListDtos.add(allwebjobs);
//		
//		//jdesc,postd,dead,no,sal,jobty,recru,skilldto
//		});
//		
//		return listOfJobListDtos;
//	}

	   
	

	
}

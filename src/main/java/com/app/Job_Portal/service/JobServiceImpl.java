package com.app.Job_Portal.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.Job_Portal.dto.JobApplicationsListDto;
import com.app.Job_Portal.dto.JobListDto;
import com.app.Job_Portal.dto.PostJobRequestDto;
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
	private JobRepository JobRepo;

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

		List<Job> list= JobRepo.findAll();
		
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
		Job job = mapper.map(postJobRequestDto, Job.class);
		// checking recruiter present or not also skills list is not empty
		if (recruiterRepository.existsById(postJobRequestDto.getRecruiterId())
				&& postJobRequestDto.getSkillIds() != null && !postJobRequestDto.getSkillIds().isEmpty()) {
			job.setPostedBy(recruiterRepository.findById(postJobRequestDto.getRecruiterId()).orElseThrow(null));
			List<Skill> skills = skillRepository.findAllById(postJobRequestDto.getSkillIds());
			job.setSkills(skills);
			job.setPostedDate(LocalDate.now());
			JobRepo.save(job);
			return "successfully job added";
		}
		return "not added job!!";
	}

	@Override
	public Map<String, Boolean> deleteJob(Long jobId) {
		Job job = JobRepo.findById(jobId)
				.orElseThrow(() -> new ResourceNotFoundException("job not found with ID: " + jobId));
		JobRepo.delete(job);

		Map<String, Boolean> response = new HashMap<>();
		response.put("Job deleted", Boolean.TRUE);
		return response;
	}

	@Override
	public String updateJob(Long jobId, UpdateJobRequestDto updateJobRequestDto) {
		Job job = JobRepo.findById(jobId)
				.orElseThrow(() -> new ResourceNotFoundException("job not found with ID: " + jobId));

		if (recruiterRepository.existsById(updateJobRequestDto.getRecruiterId())
				&& updateJobRequestDto.getSkillIds() != null && !updateJobRequestDto.getSkillIds().isEmpty()) {
			job.setJobDescription(updateJobRequestDto.getJobDescription());
			job.setDeadLineDate(updateJobRequestDto.getDeadLineDate());
			job.setPostedDate(updateJobRequestDto.getPostedDate());
			job.setJobType(updateJobRequestDto.getJobType());
			job.setNoOfJobPositions(updateJobRequestDto.getNoOfJobPositions());
			job.setSalary(updateJobRequestDto.getSalary());
			List<Skill> skills = skillRepository.findAllById(updateJobRequestDto.getSkillIds());
			job.setSkills(skills);
			JobRepo.save(job);
			return "successfully job updated";
		}
		return "job not updated!!";
	}

	@Override
	public String updateApplicationStatus(Long jobId, Long jobSeekerId, String jobStatus) {
		
		JobSeeker j= jobSeekerRepo.findById(jobSeekerId).orElseThrow(
						() -> new ResourceNotFoundException("jobSeekerId not found with ID: " + jobSeekerId));
		System.out.println(j.getJobApplications().get(0).getStatus());
		j.getJobApplications().forEach(app->{
			if(app.getJobSeeker().getJobSeekerId()==jobSeekerId)
				app.setStatus(Status.valueOf(jobStatus));
		});
		jobSeekerRepo.save(j);
				
		
		return null;
	}

	@Override
	public List<JobApplicationsListDto> getListOfJobApplications(Long jobId) {
		//to save result and return
		List<JobApplicationsListDto> jobAppListDto = new ArrayList<>();
		
		//Taking job on which list of applications are finding
		Job job = JobRepo.findById(jobId)
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

	
}

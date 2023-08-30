package com.app.Job_Portal.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.Job_Portal.dto.EducationalDetailsDto;
import com.app.Job_Portal.dto.JobListDto;
import com.app.Job_Portal.dto.JobSeekerResponseDto;
import com.app.Job_Portal.dto.RecruiterDto;
import com.app.Job_Portal.dto.SkillDto;
import com.app.Job_Portal.entities.EducationalDetails;
import com.app.Job_Portal.entities.Job;
import com.app.Job_Portal.entities.JobApplication;
import com.app.Job_Portal.entities.JobSeeker;
import com.app.Job_Portal.entities.Recruiter;
import com.app.Job_Portal.entities.Skill;
import com.app.Job_Portal.entities.User;
import com.app.Job_Portal.exceptions.ResourceNotFoundException;
import com.app.Job_Portal.repository.JobRepository;
import com.app.Job_Portal.repository.JobSeekerRepository;
import com.app.Job_Portal.repository.RecruiterRepository;
import com.app.Job_Portal.repository.UserRepository;

@Service
@Transactional 
public class AdminServiceImpl implements AdminService {

	@Autowired
	private JobSeekerRepository jobSeekerRepo;
	
	@Autowired
	private RecruiterRepository recruiterRepo;
	
	@Autowired
	private JobRepository jobrepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	public List<JobSeekerResponseDto> getAllJobseekers()
	{
		List<JobSeeker>jobSeekers=jobSeekerRepo.findAll();
		
		return jobSeekers.stream().map((jobSeeker)->{
			JobSeekerResponseDto jobSeekerHolder=mapper.map(jobSeeker, JobSeekerResponseDto.class);
			
			return jobSeekerHolder;
		}).collect(Collectors.toList());
		
	}
	
	public List<RecruiterDto> getAllRecruiters()
	{
		List<Recruiter> recruiters=recruiterRepo.findAll();
		
		return recruiters.stream().map((recruiter)->{
			RecruiterDto recruiterHolder=mapper.map(recruiter,RecruiterDto.class);
			
			JobListDto jobList=mapper.map(recruiter.getJobListings(),JobListDto.class);
			recruiterHolder.setJobListing(jobList);
			return recruiterHolder;
		}).collect(Collectors.toList());
		
		
		
		
	}
	
	public String deleteJobSeekerProfile(Long jobSeekerId)
	{
		JobSeeker jobSeeker=jobSeekerRepo.findById(jobSeekerId).orElseThrow(() -> new ResourceNotFoundException("job seeker with given id Doesn't exists"));
		for (EducationalDetails eduDetail : jobSeeker.getEduInfo()) {
            eduDetail.setJobSeeker(null);
        }
		for (Skill skill : jobSeeker.getSkills()) {
            skill.getJobSeekers().remove(jobSeeker);
        }
		for (JobApplication jobApp : jobSeeker.getJobApplications()) {
            jobApp.setJobSeeker(null);
        }
		System.out.println(jobSeeker.getEmail());
		User user=userRepo.findByEmail(jobSeeker.getEmail()).orElseThrow(() -> new ResourceNotFoundException("Job seekers with given id Doesn't exists"));
		
		userRepo.delete(user);
		jobSeekerRepo.delete(jobSeeker);
        return "jobseeker removed succefully";
		
	}
	
	public String deleteRecruiterProfile(Long recruiterId)
	{
		Recruiter recruiter=recruiterRepo.findById(recruiterId).orElseThrow(() -> new ResourceNotFoundException("Recruiter with given id Doesn't exists"));
		
		if(recruiter !=null)
		{
			List<Job> jobs=recruiter.getJobListings();
			jobrepo.deleteAll(jobs);
		
		}
	 User user=userRepo.findByEmail(recruiter.getEmail()).orElseThrow(() -> new ResourceNotFoundException("Recruiter with given id Doesn't exists"));
	 
	 userRepo.delete(user);
	 
	 recruiterRepo.delete(recruiter);
		return "Recruiter removed succefully";
		
		
	}
	
}

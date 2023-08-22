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
import com.app.Job_Portal.exceptions.ResourceNotFoundException;
import com.app.Job_Portal.repository.JobSeekerRepository;
import com.app.Job_Portal.repository.RecruiterRepository;

@Service
@Transactional 
public class AdminServiceImpl implements AdminService {

	@Autowired
	private JobSeekerRepository jobSeekerRepo;
	
	@Autowired
	private RecruiterRepository recruiterRepo;
	
	
	
	@Autowired
	private ModelMapper mapper;
	
	public List<JobSeekerResponseDto> getAllJobseekers()
	{
		List<JobSeeker>jobSeekers=jobSeekerRepo.findAll();
		
		return jobSeekers.stream().map((jobSeeker)->{
			JobSeekerResponseDto jobSeekerHolder=mapper.map(jobSeeker, JobSeekerResponseDto.class);
			
			//below 2 lines only for testing purpose because our Educational column doesn't have any data;
			//EducationalDetailsDto eduInfo=mapper.map(jobSeeker.getEduInfo(),EducationalDetailsDto.class);
			//eduInfo.setInstitute("CDAC");
			
			//below 2 lines only for testing purpose because our Skill column doesn't have any data;
           // SkillDto skillInfo=mapper.map(jobSeeker.getSkills(),SkillDto.class );
			//skillInfo.setDescription("Java Master");
			
			//jobSeekerHolder.setEduInfo(eduInfo);
			//jobSeekerHolder.setSkills(skillInfo);
			/*After adding data in edu and skill table via front-end 
			 * kindly remove line no=49,50,51,53,54,55,57,58
		    */
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
		jobSeekerRepo.delete(jobSeeker);
        return "jobseeker removed succefully";
		
	}
	
	public String deleteRecruiterProfile(Long recruiterId)
	{
		Recruiter recruiter=recruiterRepo.findById(recruiterId).orElseThrow(() -> new ResourceNotFoundException("Recruiter with given id Doesn't exists"));
		for(Job jobs:recruiter.getJobListings())
		{
			jobs.setPostedBy(null);
		}
		recruiterRepo.delete(recruiter);
		return "Recruiter removed succefully";
		
		
	}
	
}

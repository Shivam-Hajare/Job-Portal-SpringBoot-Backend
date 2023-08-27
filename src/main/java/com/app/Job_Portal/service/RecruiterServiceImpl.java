package com.app.Job_Portal.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.Job_Portal.dto.JobApplicationsListDto;
import com.app.Job_Portal.dto.JobListDto;
import com.app.Job_Portal.dto.JobStatusDto;
import com.app.Job_Portal.dto.PostJobRequestDto;
import com.app.Job_Portal.dto.RecruiterDto;
import com.app.Job_Portal.dto.RecruiterRequestDto;
import com.app.Job_Portal.dto.SkillDto;
import com.app.Job_Portal.dto.UpdateJobRequestDto;
import com.app.Job_Portal.entities.Job;
import com.app.Job_Portal.entities.JobApplication;
import com.app.Job_Portal.entities.JobSeeker;
import com.app.Job_Portal.entities.Recruiter;
import com.app.Job_Portal.entities.Skill;
import com.app.Job_Portal.entities.Status;
import com.app.Job_Portal.exceptions.ResourceNotFoundException;
import com.app.Job_Portal.exceptions.ValidationRule;
import com.app.Job_Portal.repository.JobApplicationRepository;
import com.app.Job_Portal.repository.JobRepository;
import com.app.Job_Portal.repository.JobSeekerRepository;
import com.app.Job_Portal.repository.RecruiterRepository;
import com.app.Job_Portal.repository.SkillRepository;
@Service
@Transactional
public class RecruiterServiceImpl implements RecruiterService{

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
	
	public List<JobListDto> getAllJobsPosted(Long recruiterId) {
		Recruiter recruiter= recruiterRepository.findById(recruiterId).orElseThrow(
				() -> new ResourceNotFoundException("recruiter not found with ID: " + recruiterId));;
		
				List<JobListDto> jobList=new ArrayList<JobListDto>();
				
				recruiter.getJobListings().forEach(job->{
					JobListDto newJob=mapper.map(job, JobListDto.class);
					newJob.setRecruiterName(recruiter.getFirstName()+" "+recruiter.getLastName());
					//set all skills to newJob skills using custome query
					List<String> skillsDtos=skillRepository.findSkillNamesByJobId(job.getJobId());
					newJob.setSkillsForJob_strings(skillsDtos);
					jobList.add(newJob);
				});
		return jobList;
	}


	
	
	@Override
	public Map<String, Object> deleteJobByRecruiter(Long jobId, Long recruiterId) {
	    Map<String, Object> response = new HashMap<>();

	    // Check if the job exists
	    Job job = jobRepo.findById(jobId)
	            .orElseThrow(() -> new ResourceNotFoundException("Job not found with ID: " + jobId));

	    // Check if the job was posted by the specified recruiter
	    if (!job.getPostedBy().getRecruiterId().equals(recruiterId)) {
	        response.put("success", false);
	        response.put("message", "Job not found or not posted by the specified recruiter.");
	        return response;
	    }

	    // Delete the job
	    jobRepo.delete(job);

	    response.put("success", true);
	    response.put("message", "Job deleted successfully.");
	    return response;
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
			jobRepo.save(job);
			return "successfully job added";
		}
		return "not added job!!";
	}
	
	@Override
	public String updateJobByRecruiter(Long jobId, UpdateJobRequestDto updateJobRequestDto, Long recruiterId) {
	    Job job = jobRepo.findById(jobId)
	            .orElseThrow(() -> new ResourceNotFoundException("Job not found with ID: " + jobId));

	    // Check if the job was posted by the specified recruiter
	    if (!job.getPostedBy().getRecruiterId().equals(recruiterId)) {
	        return "Job not updated. It was not posted by the specified recruiter.";
	    }

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
	        jobRepo.save(job);
	        return "Job updated successfully.";
	    }

	    return "Job not updated.";
	}


	
	public String updateApplicationStatusByRecruiter(Long jobId, Long jobSeekerId, JobStatusDto jobStatus, Long recruiterId) {
	    Job job = jobRepo.findById(jobId)
	            .orElseThrow(() -> new ResourceNotFoundException("Job not found with ID: " + jobId));

	    // Check if the job was posted by the specified recruiter
	    if (!job.getPostedBy().getRecruiterId().equals(recruiterId)) {
	        return "Job application status not updated. Job was not posted by the specified recruiter.";
	    }

	    JobSeeker jobSeeker = jobSeekerRepo.findById(jobSeekerId)
	            .orElseThrow(() -> new ResourceNotFoundException("Job Seeker not found with ID: " + jobSeekerId));

	    List<JobApplication> jobApplications = jobSeeker.getJobApplications();
	    for (JobApplication application : jobApplications) {
	        if (application.getJob().getJobId().equals(jobId)) {
	            application.setStatus(Status.valueOf(jobStatus.getJobStatus()));
	        }
	    }

	    jobSeekerRepo.save(jobSeeker);

	    return "Job application status updated.";
	}

	 public List<JobApplicationsListDto> getListOfJobApplications(Long jobId, Long loggedInRecruiterId) {
	        // To save the result and return
	        List<JobApplicationsListDto> jobAppListDto = new ArrayList<>();
	        
	        // Taking the job for which the list of applications is being fetched
	        Job job = jobRepo.findById(jobId)
	                .orElseThrow(() -> new ResourceNotFoundException("Job not found with ID: " + jobId));
	        
	        // Check if the logged-in recruiter has access to the job
	        if (!loggedInRecruiterId.equals(job.getPostedBy().getRecruiterId())) {
	            throw new ResourceNotFoundException("You are not authorized to view job applications for this job.");
	        }
	        
	        List<JobApplication> listOfApplications = job.getApplications();
	        
	        listOfApplications.forEach(jobApp -> {
	            // Mapping attributes
	            JobApplicationsListDto dto = mapper.map(jobApp, JobApplicationsListDto.class);
	            dto.setJobSeekerId(jobApp.getJobSeeker().getJobSeekerId());
	            dto.setFirstName(jobApp.getJobSeeker().getFirstName());
	            dto.setLastName(jobApp.getJobSeeker().getLastName());
	            // code for getting resume of jobseeker and seeting to jobapplicationdto dto using jobseekerId
	            
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

	 
	 @Override
	 public String updateProfile(RecruiterRequestDto recruiterDto, Long recuiterId)
	 {
		 
		 Recruiter recruiter=recruiterRepository.findById(recuiterId).orElseThrow(()->new ResourceNotFoundException("job seeker with given id not found"));
		 
		 recruiter.setFirstName(recruiterDto.getFirstName());
		 recruiter.setLastName(recruiterDto.getLastName());
		 recruiter.setPhoneNo(recruiterDto.getPhoneNo());
		 recruiter.setRecruiterBio(recruiterDto.getRecruiterBio());
		 recruiter.setCompanyName(recruiterDto.getCompanyName());
		 recruiterRepository.save(recruiter);
		 
		 return "profile updated succefully !!!"; 
	 }
	 
	 @Override
	 public RecruiterRequestDto recrutierById(Long recuiterId )
	 {
		 Recruiter recruiter=recruiterRepository.findById(recuiterId).orElseThrow(()->new ResourceNotFoundException("recruiter with given id not found"));
		 RecruiterRequestDto recru=mapper.map(recruiter, RecruiterRequestDto.class);
			return recru;
	 
	 }
	 
	 //get All Recruiters for admin contoller
	 @Override
	 public List<RecruiterDto> getAllRecruiters(Pageable pageable) {
		    
		    // Get a Page object of recruiters from the recruiterRepository repository.
		     
		    Page<Recruiter> recruiters = recruiterRepository.findAll(pageable);

		    //Create a List object to hold the RecruiterDto objects.
		     
		    List<RecruiterDto> recruiterDtos = new ArrayList<>();

		    //Iterate over the contents of the Page object and create a RecruiterDto object for each recruiter.
		     
		    for (Recruiter recruiter : recruiters.getContent()) {
		        // Create a RecruiterDto object using the mapper object.
		         
		        RecruiterDto recruiterHolder = mapper.map(recruiter, RecruiterDto.class);

		        //Set the jobListing property of the RecruiterDto object to the JobListDto object that is created from the recruiter's jobListings property.
		         
		        JobListDto jobList = mapper.map(recruiter.getJobListings(), JobListDto.class);
		        recruiterHolder.setJobListing(jobList);

		        //Add the RecruiterDto object to the List object.
		         
		        recruiterDtos.add(recruiterHolder);
		    }
		    //Return the List object.
		     
		    return recruiterDtos;
		}

}

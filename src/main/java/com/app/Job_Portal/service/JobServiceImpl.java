package com.app.Job_Portal.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.Job_Portal.dto.JobListDto;
import com.app.Job_Portal.dto.PostJobRequestDto;
import com.app.Job_Portal.dto.UpdateJobRequestDto;
import com.app.Job_Portal.entities.JobApplication;
import com.app.Job_Portal.entities.JobListing;
import com.app.Job_Portal.entities.JobSeeker;
import com.app.Job_Portal.entities.Skill;
import com.app.Job_Portal.exceptions.ResourceNotFoundException;
import com.app.Job_Portal.repository.JobApplicationRepository;
import com.app.Job_Portal.repository.JobRepository;
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

	@Override
	public List<JobListDto> getAllJobs() {
		List<JobListing> list = JobRepo.findAll();
		return list.stream().map(l -> {
			JobListDto joblistDto = mapper.map(l, JobListDto.class);

			joblistDto.setRecruiterName(l.getPostedBy().getFirstName());
			return joblistDto;
		}).collect(Collectors.toList());
	}

	@Override
	public String postJob(PostJobRequestDto postJobRequestDto) {
		JobListing job = mapper.map(postJobRequestDto, JobListing.class);
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
		JobListing job = JobRepo.findById(jobId)
				.orElseThrow(() -> new ResourceNotFoundException("job not found with ID: " + jobId));
		JobRepo.delete(job);

		Map<String, Boolean> response = new HashMap<>();
		response.put("Job deleted", Boolean.TRUE);
		return response;
	}

	@Override
	public String updateJob(Long jobId, UpdateJobRequestDto updateJobRequestDto) {
		JobListing job = JobRepo.findById(jobId)
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
	public String updateApplicationStatus(Long jobId, Long jobSeekerId, @Valid String jobStatus) {
		JobListing job = JobRepo.findById(jobId)
				.orElseThrow(() -> new ResourceNotFoundException("job not found with ID: " + jobId));
		List<JobApplication> applicatioList = job.getApplications();

		return null;
	}

}

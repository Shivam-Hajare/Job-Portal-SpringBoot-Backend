package com.app.Job_Portal.service;


import com.app.Job_Portal.dto.*;
import com.app.Job_Portal.entities.*;
import com.app.Job_Portal.exceptions.ResourceNotFoundException;
import com.app.Job_Portal.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class JobSeekerServiceImpl implements JobSeekerService {

    @Autowired
    private JobSeekerRepository jobSeekerRepo;

    @Autowired
    private JobRepository jobRepo;

    @Autowired
    private JobApplicationRepository jobApplicationRepo;

    @Autowired
    private SkillRepository skillRepo;

    @Autowired
    private EducationalDetailsRepository educationalDetailsRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<JobListDto> getAllJobs() {

        List<Job> jobLists = jobRepo.findAll();

        return jobLists.stream()
                .map((job) -> {
                    JobListDto jobHolder = mapper.map(job, JobListDto.class);
                    jobHolder.setRecruiterName(job.getPostedBy().getFirstName());
                    return jobHolder;
                })
                .collect(Collectors.toList());
    }


    @Override
    public List<JobListDto> getJobsWithGivenType(String jobType) {

        List<Job> jobLists = jobRepo.findAll();

        JobType typeOfJob = JobType.valueOf(jobType);

        return jobLists.stream()
                .map((job) -> {
                    JobListDto jobHolder = null;
                    if (job.getJobType().equals(typeOfJob)) {
                        jobHolder = mapper.map(job, JobListDto.class);
                        jobHolder.setRecruiterName(job.getPostedBy().getFirstName());

                    }
                    return jobHolder;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }


    @Override
    public List<JobListDto> getJobsWithGivenTitle(String title) {

        List<Job> jobLists = jobRepo.findAll();

        return jobLists.stream()
                .map((job) -> {
                    JobListDto jobHolder = null;
                    if (job.getJobTitle().equals(title)) {
                        jobHolder = mapper.map(job, JobListDto.class);
                        jobHolder.setRecruiterName(job.getPostedBy().getFirstName());

                    }
                    return jobHolder;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }


    @Override
    public List<JobApplicationResponseDto> getAppliedJobs(Long jobSeekerId) {

        JobSeeker seeker = jobSeekerRepo.findById(jobSeekerId).orElseThrow(() -> new ResourceNotFoundException("job seeker with given id not found"));

        List<JobApplication> listOfApplications = seeker.getJobApplications();

        return listOfApplications.stream()
                .map((application) -> {
                    JobApplicationResponseDto responseDtoHolder = mapper.map(application.getJob(), JobApplicationResponseDto.class);

                    responseDtoHolder.setApplicationId(application.getApplicationId());
                    responseDtoHolder.setAppliedDate(application.getAppliedDate());
                    responseDtoHolder.setStatus(application.getStatus());
                    responseDtoHolder.setPostedBy(application.getJob().getPostedBy().getFirstName() + " " + application.getJob().getPostedBy().getLastName());
                    responseDtoHolder.setSkillsForJob(application.getJob().getSkills());
                    return responseDtoHolder;
                })
                .collect(Collectors.toList());
    }


    @Override
    public List<JobApplicationResponseDto> getAllAcceptedJobs(Long jobSeekerId) {
        JobSeeker seeker = jobSeekerRepo.findById(jobSeekerId).orElseThrow(() -> new ResourceNotFoundException("job seeker with given id not found"));

        List<JobApplication> listOfApplications = seeker.getJobApplications();

        return listOfApplications.stream()
                .map((application) -> {
                    JobApplicationResponseDto responseDtoHolder = mapper.map(application.getJob(), JobApplicationResponseDto.class);

                    responseDtoHolder.setApplicationId(application.getApplicationId());
                    responseDtoHolder.setAppliedDate(application.getAppliedDate());
                    responseDtoHolder.setStatus(application.getStatus());
                    responseDtoHolder.setPostedBy(application.getJob().getPostedBy().getFirstName() + " " + application.getJob().getPostedBy().getLastName());
                    responseDtoHolder.setSkillsForJob(application.getJob().getSkills());
                    return responseDtoHolder;
                })
                .filter(application -> application.getStatus().toString().equals("ACCEPTED"))
                .collect(Collectors.toList());
    }


    @Override
    public String applyForAJob(Long jobId, Long jobSeekerId) {

        JobSeeker seeker = jobSeekerRepo.findById(jobSeekerId).orElseThrow(() -> new ResourceNotFoundException("job seeker with given id not found"));
        Job job = jobRepo.findById(jobId).orElseThrow(() -> new ResourceNotFoundException("selected Job doesn't exists"));

        // check if already applied for the job
        for(JobApplication applicationHolder: job.getApplications()) {
            if(applicationHolder.getJobSeeker().getJobSeekerId().equals(seeker.getJobSeekerId())) {
                 return "Already applied for this job";
             }
        }

        JobApplication application = new JobApplication();
        application.setJobSeeker(seeker);
        application.setJob(job);
        application.setStatus(Status.PENDING);
        application.setAppliedDate(LocalDate.now());
        seeker.submitAnApplication(application, job);

        jobApplicationRepo.save(application);

        return "Succefully applied for a job";

    }


    @Override
    public String withDrawAnApplication(Long jobId, Long jobSeekerId) {
        JobSeeker seeker = jobSeekerRepo.findById(jobSeekerId).orElseThrow(() -> new ResourceNotFoundException("job seeker with given id not found"));
        Job job = jobRepo.findById(jobId).orElseThrow(() -> new ResourceNotFoundException("selected Job doesn't exists"));

        boolean removeAnApplication = false;
        JobApplication application = new JobApplication();

        // check applied or not
        for(JobApplication applicationHolder: job.getApplications()) {
            if(applicationHolder.getJobSeeker().getJobSeekerId().equals(seeker.getJobSeekerId())) {
               // pending application can be withdrawn
                if(applicationHolder.getStatus().equals(Status.PENDING)) {
                    application = applicationHolder;
                    removeAnApplication = true;
                    break;
                }else {
                    // already selected or rejected application cannot be withdrawn
                    return "cannot withdrawn already selected or rejected application";
                }

            }
        }

        // application exists and can be removed
        if(removeAnApplication) {
            seeker.withDrawAnApplication(application, job);
            return "application withdrawn succefully";
        }

        // application does not exists
        return "selected application does not exists ";
    }

//  INCOMPLETE
    @Override
    public String createProfile(JobSeekerRequestDto seekerDto) {
        JobSeeker seekerProfile = mapper.map(seekerDto, JobSeeker.class);
        return "null";
    }


    @Override
    public String updateProfile(JobSeekerRequestDto seekerDto, Long jobSeekerId) {

        JobSeeker seeker = jobSeekerRepo.findById(jobSeekerId).orElseThrow(()-> new ResourceNotFoundException("job seeker with given id not found"));

        seeker.setFirstName(seekerDto.getFirstName());
        seeker.setLastName(seekerDto.getLastName());
        seeker.setYearOfExperience(seekerDto.getYearOfExperience());

        // Update skills
        List<SkillDto> skillDtos = seekerDto.getSkills();
        if (skillDtos != null) {
            List<Skill> skills = skillRepo.findAllByNameIn(skillDtos.stream().map(SkillDto::getName).toList());
            seeker.setSkills(skills);
        }

        // Update educational details
        List<EducationalDetailsDto> educationalDetailsDtos = seekerDto.getEduInfo();
        if (educationalDetailsDtos != null) {

            // Clear previous educational details
            seeker.getEduInfo().clear();

            List<EducationalDetails> educationalDetails = educationalDetailsDtos.stream()
                    .map(dto ->  {
                        EducationalDetails seekerEducation =  mapper.map(dto, EducationalDetails.class);
                        seekerEducation.setJobseeker(seeker);
                        return seekerEducation;
                    })
                    .collect(Collectors.toList());
            educationalDetailsRepo.saveAll(educationalDetails);
            jobSeekerRepo.save(seeker);
        }

        return "profile updated succefully";
    }


}
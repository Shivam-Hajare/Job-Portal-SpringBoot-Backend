package com.app.Job_Portal.service;


import com.app.Job_Portal.dto.JobApplicationResponseDto;
import com.app.Job_Portal.dto.JobListDto;
import com.app.Job_Portal.entities.JobApplication;
import com.app.Job_Portal.entities.JobListing;
import com.app.Job_Portal.entities.JobSeeker;
import com.app.Job_Portal.entities.JobType;
import com.app.Job_Portal.exceptions.ResourceNotFoundException;
import com.app.Job_Portal.repository.JobSeekerRepository;
import com.app.Job_Portal.repository.JobListingRepository;
import com.sun.tools.jconsole.JConsoleContext;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class JobSeekerServiceImpl implements JobSeekerService{

        @Autowired
        private JobSeekerRepository jobSeekerRepo;

        @Autowired
        private JobListingRepository jobListingRepo;

        @Autowired
        private ModelMapper mapper;

        @Override
        public List<JobListDto> getAllJobs() {

                List<JobListing> jobLists = jobListingRepo.findAll();

                List<JobListDto> allJobs = jobLists.stream()
                        .map( (job) -> {
                        JobListDto  jobHolder = mapper.map(job, JobListDto.class);
                        jobHolder.setRecruiterName(job.getPostedBy().getFirstName());
                        return jobHolder;
                        } )
                        .collect(Collectors.toList());

                return allJobs;
        }

        @Override
        public List<JobListDto> getJobsWithGivenType(String jobType) {

                List<JobListing> jobLists = jobListingRepo.findAll();

                JobType typeOfJob = JobType.valueOf(jobType);

                List<JobListDto> allJobsByType = jobLists.stream()
                        .map( (job) -> {
                                JobListDto  jobHolder = null;
                                if(job.getJobType().equals(typeOfJob)) {
                                 jobHolder = mapper.map(job, JobListDto.class);
                                jobHolder.setRecruiterName(job.getPostedBy().getFirstName());

                                }
                                return jobHolder;
                        } )
                        .filter(job-> job != null)
                        .collect(Collectors.toList());

                return allJobsByType;
        }

    @Override
    public List<JobListDto> getJobsWithGivenTitle(String title) {

        List<JobListing> jobLists = jobListingRepo.findAll();

        List<JobListDto> allJobsByTitle = jobLists.stream()
                .map( (job) -> {
                    JobListDto  jobHolder = null;
                    if(job.getJobTitle().equals(title)) {
                        jobHolder = mapper.map(job, JobListDto.class);
                        jobHolder.setRecruiterName(job.getPostedBy().getFirstName());

                    }
                    return jobHolder;
                } )
                .filter(job-> job != null)
                .collect(Collectors.toList());

        return allJobsByTitle;
    }

    @Override
    public List<JobListDto> getappliedJobs(Long jobseekerId) {

        JobSeeker seeker = jobSeekerRepo.findById(jobseekerId).orElseThrow(() ->  new ResourceNotFoundException("job seeker with given id not found"));

        List<JobApplication>listOfApplications  =  seeker.getJobApplications();

//        List<JobApplicationResponseDto> listOfJobApplicationDtos = listOfApplications.stream()
//                .map((application) -> {
//
//                    JobApplicationResponseDto jobApplication = mapper.map(application,JobApplicationResponseDto.class);
//
//                     jobApplication.setJobListingId(application.getJob().getJobListingId());
//
//                    jobApplication.setJobTitle(application.getJob().getJobTitle());
//
//                    jobApplication.setJobDescription(application.getJob().getJobDescription());
//
//                    jobApplication.setPostedDate(application.getJob().getPostedDate());
//
//                    jobApplication.setDeadLineDate(application.getJob().getDeadLineDate());
//
//                    jobApplication.setNoOfJobPositions(application.getJob().getNoOfJobPositions());
//
//                    jobApplication.setSalary(application.getJob().getSalary());
//
//                    jobApplication.setJobType(application.getJob().getJobType());
//
//                    jobApplication.setPostedBy(application.getJob().getPostedBy().getFirstName());
//
//                    jobApplication.setJobListingId(application.getJob().getJobListingId());
//                    return jobApplication;
//                })
//                .collect(Collectors.toList());

        List<JobApplicationResponseDto> listOfJobApplicationDtos = listOfApplications.stream()
               .map((application) -> {
                   JobApplicationResponseDto jobApplication = mapper.map(application.getJob(),JobApplicationResponseDto.class);

                   return jobApplication;
               })
                .collect(Collectors.toList());
        System.out.println(listOfJobApplicationDtos);
        return null;
    }
}

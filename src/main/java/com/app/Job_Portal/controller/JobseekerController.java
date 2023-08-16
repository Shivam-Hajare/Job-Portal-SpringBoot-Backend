package com.app.Job_Portal.controller;


import com.app.Job_Portal.dto.JobListDto;
import com.app.Job_Portal.entities.JobListing;
import com.app.Job_Portal.service.JobSeekerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/jobseeker")
public class JobseekerController
{

    @Autowired
    private JobSeekerService jobSeekerService;

    /**
     *
     * @Path        /get/all
     * @param        None
     * @return       Response Entity of JobListDto type
     * Description : This method return List of all jobs with JobListDto.
     * @GetMapping: Annotation for mapping HTTP GET requests onto specific handler methods.
     *
     */
    @GetMapping("/get/all")
    public List<JobListDto> getAllJobs() {
        return jobSeekerService.getAllJobs();
    }


    /**
     *
     *  @Path         /get/jobtype/{jobType}
     * @param         None
     * @PathVariable  jobType
     * @return        Response Entity of JobListDto type
     * Description :  This method return List of jobs with given job type
     * @GetMapping:   Annotation for mapping HTTP GET requests onto specific handler methods.
     *
     */


    @GetMapping("/get/jobtype/{jobType}")
    public List<JobListDto> getJobsByJobType(@PathVariable String jobType) {

        return jobSeekerService.getJobsWithGivenType(jobType);

    }

    /**
     *
     *  @Path         /get/title/{title}
     * @param         None
     * @PathVariable  title
     * @return        Response Entity of JobListDto type
     * Description :  This method return List of jobs with given title type
     * @GetMapping:   Annotation for mapping HTTP GET requests onto specific handler methods.
     *
     */

    @GetMapping("/get/title/{title}")
    public List<JobListDto> getJobsByJobTitle(@PathVariable String title) {

        return jobSeekerService.getJobsWithGivenTitle(title);

    }


    /**
     *
     *  @Path         /get/title/{title}
     * @param         None
     * @PathVariable  title
     * @return        Response Entity of JobListDto type
     * Description :  This method return List of jobs with given title type
     * @GetMapping:   Annotation for mapping HTTP GET requests onto specific handler methods.
     *
     */
//    public ResponseEntity applyForJob() {
//
//    }

    /* INCOMPLETE */   // not able to test
    /**
     *
     *  @Path         /get/applied/{jobseekerId}
     * @param         None
     * @PathVariable  jobseekerId
     * @return        Response Entity of JobListDto type
     * Description :  This method return List of applied jobs
     * @GetMapping:   Annotation for mapping HTTP GET requests onto specific handler methods.
     *
     */


    /* INCOMPLETE */   // not able to test
    // haven't added any job so not getting write a post api first
    @GetMapping("/get/applied/{jobseekerId}")
    public List<JobListDto> getJobsByJobTitle(@PathVariable Long jobseekerId) {

        return jobSeekerService.getappliedJobs(jobseekerId);

    }
}

package com.app.Job_Portal.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.app.Job_Portal.entities.Job;

import com.app.Job_Portal.dto.PostJobRequestDto;
import com.app.Job_Portal.dto.UpdateJobRequestDto;
import com.app.Job_Portal.service.JobService;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobServiceImpl;

    @GetMapping("/findAllJobs")
    public ResponseEntity<?>  getAllJobs() {
        System.out.println("in find all jobs!!!");
        return new ResponseEntity<>(jobServiceImpl.getAllJobs(), HttpStatus.OK);
    }

    /**
	 * 
	 * @param        PostJobRequestDto
	 * @return       Response Entity of Object type
	 * Description : This method posts a new job.
	 * @PostMapping: Annotation for mapping HTTP POST requests onto specific handler methods.
	 * 
	 */
    @PostMapping("/postJob")
    public ResponseEntity<?>postJob(@RequestBody @Valid PostJobRequestDto postJobRequestDto )
    {
    	System.out.println(postJobRequestDto);
    	String rst=jobServiceImpl.postJob(postJobRequestDto);
    	return new ResponseEntity<>(rst, HttpStatus.OK);
    }
    
    //delete job from job list
    @DeleteMapping("/{id}")
    public ResponseEntity<?>deleteJob(@PathVariable(value = "id")Long jobId)
    {
    	System.out.println(jobId);
    	
    	Map<String, Boolean> response = jobServiceImpl.deleteJob(jobId);
    	
    	return new ResponseEntity<>(response, HttpStatus.OK);
    }
    //Edit job details
    @PutMapping("/{id}")
    public ResponseEntity<?>updateJob(@PathVariable(value = "id") Long jobId, @Valid @RequestBody UpdateJobRequestDto updateJobRequestDto)
    {
    	System.out.println(jobId+" "+updateJobRequestDto);
    	String rst=jobServiceImpl.updateJob(jobId,updateJobRequestDto);
    	return new ResponseEntity<>(rst, HttpStatus.OK);
    }
    
    //accept/reject application
    //working on this!!!not completed
    @PutMapping("/{jobId}/{jobSeekerId}")
    public ResponseEntity<?>updateApplicationStatus(@PathVariable(value = "jobId") Long jobId,@PathVariable(value = "jobSeekerId") Long jobSeekerId, @Valid @RequestBody String jobStatus)
    {
    	System.out.println("jobStatus "+jobStatus+" jobSeekerId: "+jobSeekerId+" jobId: "+jobId);
    	
    	String rst=jobServiceImpl.updateApplicationStatus(jobId,jobSeekerId,jobStatus);
    	return new ResponseEntity<>(rst, HttpStatus.OK);
    }
}

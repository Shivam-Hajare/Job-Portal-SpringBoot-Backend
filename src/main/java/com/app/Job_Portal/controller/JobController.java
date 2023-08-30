package com.app.Job_Portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Job_Portal.service.JobService;

@RestController
@RequestMapping("/jobs")
@CrossOrigin
public class JobController {

    @Autowired
    private JobService jobServiceImpl;

    @GetMapping("/findAllJobs")
    public ResponseEntity<?>  getAllJobs() {
        return new ResponseEntity<>(jobServiceImpl.getAllJobs(), HttpStatus.OK);
    }

    @GetMapping("/findJobsWithTitle/{web}")
    public ResponseEntity<?>  getAllJobsWithTitle(@PathVariable String web) {
       return new ResponseEntity<>(jobServiceImpl.JobsWithTitle(web), HttpStatus.OK);
    }

}

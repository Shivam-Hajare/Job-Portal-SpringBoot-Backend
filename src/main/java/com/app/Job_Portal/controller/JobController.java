package com.app.Job_Portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Job_Portal.entities.Job;
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

}

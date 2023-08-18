package com.app.Job_Portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.app.Job_Portal.service.RecruiterServiceImpl;

@RestController

public class RecruiterController {

	@Autowired
	private RecruiterServiceImpl reServiceImpl;
	
	 @GetMapping("/AllJobsPosted/{RecruiterId}")
	    public ResponseEntity<?>  getAllJobsPosted(@PathVariable(value = "RecruiterId") Long RecruiterId) {
	    
	        return new ResponseEntity<>(reServiceImpl.getAllJobsPosted(RecruiterId), HttpStatus.OK);
	    }
}

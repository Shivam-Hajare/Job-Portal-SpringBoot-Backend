package com.app.Job_Portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.app.Job_Portal.dto.JobSeekerResponseDto;
import com.app.Job_Portal.dto.RecruiterDto;
import com.app.Job_Portal.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@GetMapping("/get/all/jobseeker")
	public List<JobSeekerResponseDto> getAllJobSeeker()
	{
		return adminService.getAllJobseekers();
	}
	
	@GetMapping("/get/all/recruiter")
	public List<RecruiterDto> getAllRecruiter()
	{
		return adminService.getAllRecruiters();
	}
	
	@DeleteMapping("/remove-jobseeker-profile/{jobSeekerId}")
	public ResponseEntity<String> removeJobSeekerProfile(@PathVariable Long jobSeekerId)
	{
	return new ResponseEntity<String>(adminService.deleteJobSeekerProfile(jobSeekerId),HttpStatus.OK); 	
	}
	
	@DeleteMapping("/remove-recruiter-profile/{recruiterId}")
	public ResponseEntity<String> removeRecruiterProfile(@PathVariable Long recruiterId)
	{
		return new ResponseEntity<String>(adminService.deleteRecruiterProfile(recruiterId),HttpStatus.OK);
	}
	
	
	
	
	
}

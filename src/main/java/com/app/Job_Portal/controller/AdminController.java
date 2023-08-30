package com.app.Job_Portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.Job_Portal.dto.JobSeekerResponseDto;
import com.app.Job_Portal.dto.RecruiterDto;
import com.app.Job_Portal.service.AdminService;
import com.app.Job_Portal.service.RecruiterService;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private RecruiterService resService;
	
	
	@GetMapping("/get/all/jobseeker")
	public List<JobSeekerResponseDto> getAllJobSeeker()
	{
		return adminService.getAllJobseekers();
	}
	
//	@GetMapping("/get/all/recruiter")
//	public List<RecruiterDto> getAllRecruiter()
//	{
//		return adminService.getAllRecruiters();
//	}
	@GetMapping("/get/all/recruiter")
    public List<RecruiterDto> getAllRecruiters(@RequestParam(value = "page", defaultValue = "0") int page,
                                          @RequestParam(value = "size", defaultValue = "10") int size) {
        return resService.getAllRecruiters(PageRequest.of(page, size));
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

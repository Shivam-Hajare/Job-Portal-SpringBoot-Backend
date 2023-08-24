package com.app.Job_Portal.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.Job_Portal.dto.JobApplicationsListDto;
import com.app.Job_Portal.dto.JobStatusDto;
import com.app.Job_Portal.dto.PostJobRequestDto;
import com.app.Job_Portal.dto.RecruiterRequestDto;
import com.app.Job_Portal.dto.UpdateJobRequestDto;
import com.app.Job_Portal.service.RecruiterServiceImpl;

@RestController
@RequestMapping("/recruiters")
@CrossOrigin
public class RecruiterController {

	@Autowired
	private RecruiterServiceImpl reServiceImpl;
	
	
	 @GetMapping("/AllJobsPosted/{RecruiterId}")
	    public ResponseEntity<?>  getAllJobsPosted(@PathVariable(value = "RecruiterId") Long RecruiterId) {
	    
	        return new ResponseEntity<>(reServiceImpl.getAllJobsPosted(RecruiterId), HttpStatus.OK);
	    }
	 
	 //delete job from job list
	 @DeleteMapping("/{recruiterId}/jobs/{jobId}")
	    public ResponseEntity<?>deleteJob(  @PathVariable Long recruiterId,

	    	    @PathVariable Long jobId)
	    {
		 	Map<String, Object> response = reServiceImpl.deleteJobByRecruiter(jobId,recruiterId);
	    	
	    	return new ResponseEntity<>(response, HttpStatus.OK);
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
	    	String rst=reServiceImpl.postJob(postJobRequestDto);
	    	return new ResponseEntity<>(rst, HttpStatus.OK);
	    }
	    
	    //Edit job details
	    @PutMapping("/{recruiterId}/jobs/{jobId}")
	    public ResponseEntity<?> updateJobByRecruiter(
	            @PathVariable Long recruiterId,
	            @PathVariable Long jobId,
	            @Valid @RequestBody UpdateJobRequestDto updateJobRequestDto) {

	        String result = reServiceImpl.updateJobByRecruiter(jobId, updateJobRequestDto, recruiterId);
	        return new ResponseEntity<>(result, HttpStatus.OK);
	    }
	    
	  //accept/reject application
	    @PutMapping("/{recruiterId}/jobs/{jobId}/jobSeekers/{jobSeekerId}")
	    public ResponseEntity<?> updateApplicationStatusByRecruiter(
	            @PathVariable Long recruiterId,
	            @PathVariable Long jobId,
	            @PathVariable Long jobSeekerId,
	            @RequestBody JobStatusDto jobStatus) {
	        String result = reServiceImpl.updateApplicationStatusByRecruiter(jobId, jobSeekerId, jobStatus, recruiterId);
	        return new ResponseEntity<>(result, HttpStatus.OK);
	    }

	    //checking job application for perticular job
	    @GetMapping("/JobApplications/{jobId}/{recruiterId}")
	    public ResponseEntity<List<JobApplicationsListDto>> getAllJobApplications(
	            @PathVariable(value = "jobId") Long jobId,
	            @PathVariable(value = "recruiterId") Long recruiterId
	    ) {
	        List<JobApplicationsListDto> jobAppList = reServiceImpl.getListOfJobApplications(jobId, recruiterId);
	        return ResponseEntity.ok(jobAppList);
	    }
	    
	    @PutMapping("/update-profile/{recruiterId}")
	    public ResponseEntity<String> updateProfile(@PathVariable Long recruiterId, @RequestBody RecruiterRequestDto recruiterDto)
	    {
	    return new ResponseEntity<String>(reServiceImpl.updateProfile(recruiterDto, recruiterId),HttpStatus.OK);	
	    }
	    
}

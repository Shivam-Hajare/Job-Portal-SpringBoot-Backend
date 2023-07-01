package com.app.Job_Portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Job_Portal.dto.PostJobRequestDto;
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
	 * @param        jobListDto
	 * @return       Response Entity of Object type
	 * Description : This method posts a new job.
	 * @PostMapping: Annotation for mapping HTTP POST requests onto specific handler methods.
	 * 
	 */
//    @PostMapping("/postJob")
//    public ResponseEntity<?>postJob(@RequestBody PostJobRequestDto postJobRequestDto )
//    {
//    	System.out.println(postJobRequestDto);
//    	String rst=jobServiceImpl.postJob(postJobRequestDto);
//    	return new ResponseEntity<>(rst, HttpStatus.OK);
//    }
}

package com.app.Job_Portal.controller;


import com.app.Job_Portal.dto.JobApplicationResponseDto;
import com.app.Job_Portal.dto.JobListDto;
import com.app.Job_Portal.dto.JobSeekerRequestDto;
import com.app.Job_Portal.dto.JobSeekerResponseDto;
import com.app.Job_Portal.service.JobSeekerService;
import com.app.Job_Portal.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/jobseeker")
@CrossOrigin
public class JobseekerController {

    @Autowired
    private JobSeekerService jobSeekerService;

    @Autowired
    private ResumeService resumeService;

    /*
     * @Path        /get/all
     * @param        None
     * @return       Response Entity of JobListDto type
     * Description : This method return List of all jobs with JobListDto.
     * @GetMapping : Annotation for mapping HTTP GET requests onto specific handler methods.
     */

    @GetMapping("/get/all")
    public List<JobListDto> getAllJobs() {
        return jobSeekerService.getAllJobs();
    }


    /*
     * @Path         /get/jobtype/{jobType}
     * @param         None
     * @PathVariable  jobType
     * @return        Response Entity of JobListDto type
     * Description :  This method return List of jobs with given job type
     * @GetMapping :   Annotation for mapping HTTP GET requests onto specific handler methods.
     */

    @GetMapping("/get/jobtype/{jobType}")
    public List<JobListDto> getJobsByJobType(@PathVariable String jobType) {

        return jobSeekerService.getJobsWithGivenType(jobType);

    }


    /*
     * @Path         /get/title/{title}
     * @param         None
     * @PathVariable  title
     * @return        Response Entity of JobListDto type
     * Description :  This method return List of jobs with given title type
     * @GetMapping :   Annotation for mapping HTTP GET requests onto specific handler methods.
     */

    @GetMapping("/get/title/{title}")
    public List<JobListDto> getJobsByJobTitle(@PathVariable String title) {

        return jobSeekerService.getJobsWithGivenTitle(title);

    }


    /*
     * @Path         /get/applied/{jobSeekerId}
     * @param         None
     * @PathVariable  jobSeekerId
     * @return        Response Entity of JobApplicationResponseDto type
     * Description :  This method return List of applied jobs
     * @GetMapping :   Annotation for mapping HTTP GET requests onto specific handler methods.
     */

    @GetMapping("/get/applied/{jobSeekerId}")
    public List<JobApplicationResponseDto> getAllAppliedJobs(@PathVariable Long jobSeekerId) {

        return jobSeekerService.getAppliedJobs(jobSeekerId);

    }


    /*
     * @Path         /get/accepted/{jobSeekerId}
     * @param         None
     * @PathVariable  jobSeekerId
     * @return        Response Entity of JobApplicationResponseDto type
     * Description :  This method return List of accepted jobs
     * @GetMapping :   Annotation for mapping HTTP GET requests onto specific handler methods.
     */

    @GetMapping("/get/accepted/{jobSeekerId}")
    public List<JobApplicationResponseDto> getAllAcceptedJobs(@PathVariable Long jobSeekerId) {

        return jobSeekerService.getAllAcceptedJobs(jobSeekerId);

    }


    /*
     * @Path         /apply/{jobId}/{jobSeekerId}
     * @param         None
     * @PathVariable  jobId, jobSeekerId
     * @return        Response Entity with message
     * Description :  This method returns a message to tell whether applied succefully or not
     * @GetMapping :   Annotation for mapping HTTP GET requests onto specific handler methods.
     */

    @GetMapping("/apply/{jobId}/{jobSeekerId}")
    public ResponseEntity<String> applyForAJob(@PathVariable Long jobId, @PathVariable Long jobSeekerId) {
        return new ResponseEntity<String>(jobSeekerService.applyForAJob(jobId, jobSeekerId), HttpStatus.OK);
    }


    /*
     * @Path         /withdraw-application/{jobId}/{jobSeekerId}
     * @param         None
     * @PathVariable  jobId, jobSeekerId
     * @return        Response Entity with message
     * Description :  This method returns a message to tell whether cancellation is succefull or not
     * @DeleteMapping :  Annotation for mapping HTTP DELETE requests onto specific handler methods.
     */

    @DeleteMapping("/withdraw-application/{jobId}/{jobSeekerId}")
    public ResponseEntity<String> withDrawAnApplication(@PathVariable Long jobId, @PathVariable Long jobSeekerId) {
        return new ResponseEntity<String>(jobSeekerService.withDrawAnApplication(jobId, jobSeekerId), HttpStatus.OK);
    }

    /*
     * @Path         /create-profile
     * @param         resume
     * @PathVariable  None
     * @return        Response Entity with message
     * Description :  This method returns a message to tell whether profile created succefully or not
     * @PostMapping :   Annotation for mapping HTTP POST requests onto specific handler methods.
     */

    @PostMapping("/create-profile")
    public ResponseEntity<String> createProfile(@RequestBody JobSeekerRequestDto seekerDto) {
        return new ResponseEntity<String>(jobSeekerService.createProfile(seekerDto), HttpStatus.OK);
    }

    /*
     * @Path         /update-profile
     * @param         None
     * @PathVariable  jobSeekerId
     * @return        Response Entity with message
     * Description :  This method returns a message to tell whether profile updated succefully
     * @PutMapping:   Annotation for mapping HTTP POST requests onto specific handler methods.
     */

    @PutMapping("/update-profile/{jobSeekerId}")
    public ResponseEntity<String> updateProfile(@PathVariable Long jobSeekerId, @RequestBody JobSeekerRequestDto seekerDto) {
        return new ResponseEntity<String>(jobSeekerService.updateProfile(seekerDto, jobSeekerId), HttpStatus.OK);
    }


    /*
     * @Path         /remove-profile
     * @param         None
     * @PathVariable  jobSeekerId
     * @return        Response Entity with message
     * Description :  This method returns a message to tell whether profile updated succefully
     * @DeleteMapping :   Annotation for mapping HTTP Delete requests onto specific handler methods.
     */

    @DeleteMapping("/remove-profile/{jobSeekerId}")
    public ResponseEntity<String> removeProfile(@PathVariable Long jobSeekerId) {
        return new ResponseEntity<String>(jobSeekerService.deleteProfile(jobSeekerId), HttpStatus.OK);
    }


    /*
     * @Path         /get-profile
     * @param         None
     * @PathVariable  jobSeekerId
     * @return        JobSeekerResponseDto
     * Description :  This method returns a jobSeeker's information with given id
     * @GetMapping :  Annotation for mapping HTTP Get requests onto specific handler methods.
     */

    @GetMapping("/get-profile/{jobSeekerId}")
    public JobSeekerResponseDto getProfile(@PathVariable Long jobSeekerId) {
        return jobSeekerService.getProfile(jobSeekerId);
    }


    /*
     * @Path         /get-profile/resume/{jobSeekerId}
     * @param         None
     * @PathVariable  jobSeekerId
     * @return        InputStream
     * Description :  This method returns a resume pdf as inputStream
     * @GetMapping :  Annotation for mapping HTTP Get requests onto specific handler methods.
     */

    @GetMapping("/get-profile/resume/{jobSeekerId}")
    public void getResume(@PathVariable Long jobSeekerId, HttpServletResponse res) throws IOException {
        InputStream resource = resumeService.getResume(jobSeekerId);
        res.setContentType(String.valueOf(MediaType.APPLICATION_PDF));
        StreamUtils.copy(resource, res.getOutputStream());
    }

    /*
     * @Path         /upload/resume/{jobSeekerId}
     * @param         None
     * @PathVariable  jobSeekerId
     * @return        InputStream
     * Description :  This method returns a resume pdf as inputStream
     * @GetMapping :  Annotation for mapping HTTP Get requests onto specific handler methods.
     */

    @GetMapping("/upload/resume/{jobSeekerId}")
    public ResponseEntity<String> uploadResume(@PathVariable Long jobSeekerId, @RequestParam MultipartFile resume) throws IOException {
        return new ResponseEntity<String>(resumeService.uploadResume(resume, jobSeekerId), HttpStatus.OK);
    }

    /*
     * @Path         /remove/resume/{jobSeekerId}
     * @param         None
     * @PathVariable  jobSeekerId
     * @return        ResponseEntity<String>
     * Description :  This method returns a String message
     * @GetMapping :  Annotation for mapping HTTP Get requests onto specific handler methods.
     */

    @DeleteMapping("/remove/resume/{jobSeekerId}")
    public ResponseEntity<String> removeResume(@PathVariable Long jobSeekerId) {
        return new ResponseEntity<String>(resumeService.removeResume(jobSeekerId), HttpStatus.OK);
    }


}

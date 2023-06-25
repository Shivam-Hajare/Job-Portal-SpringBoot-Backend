package com.app.Job_Portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Job_Portal.entities.JobListing;
import com.app.Job_Portal.service.JobService;

@RestController
@RequestMapping("/jobs")
public class JobController {

	@Autowired
	private JobService jobServiceImpl;
	

  @GetMapping("/hello")
  public String Helo() {
	  return "hello world";
  }

    @GetMapping("/home")
    public String home() {
        return "home added ";
    }
}

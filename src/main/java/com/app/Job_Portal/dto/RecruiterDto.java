package com.app.Job_Portal.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RecruiterDto {

	private Long recruiterId;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNo;
    private String companyName;
    
    private List<JobListDto> jobListings= new ArrayList<>();
	
    public void setJobListing(JobListDto jobs)
    {
    	jobListings.add(jobs);
    }
    
}

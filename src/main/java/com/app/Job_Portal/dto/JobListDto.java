package com.app.Job_Portal.dto;

import java.time.LocalDate;

import com.app.Job_Portal.entities.JobType;
import com.app.Job_Portal.entities.Status;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JobListDto {

	private String jobTitle;

	private String jobDescription;

	private LocalDate postedDate;

	private LocalDate deadLineDate;

	private int noOfJobPositions;

	private double salary;

	private JobType jobType;

	//private Recruiter postedBy;
	private String recruiterName;

}

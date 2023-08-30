package com.app.Job_Portal.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.app.Job_Portal.entities.JobType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JobListDto {
	
	private Long jobId;
	
	 private String companyName;

	private String jobTitle;

	private String jobDescription;

	private LocalDate postedDate;

	private LocalDate deadLineDate;

	private int noOfJobPositions;

	private double salary;

	private JobType jobType;

	//private Recruiter postedBy;
	private String recruiterName;
	
 private List<SkillDto> skillsForJob = new ArrayList<>();
 private List<String> skillsForJob_strings = new ArrayList<>();

}

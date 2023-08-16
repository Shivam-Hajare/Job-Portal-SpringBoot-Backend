package com.app.Job_Portal.dto;

import java.time.LocalDate;
import java.util.List;

import com.app.Job_Portal.entities.JobType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UpdateJobRequestDto {
private long recruiterId;
	
	private String jobTitle;

	private String jobDescription;

	private LocalDate deadLineDate;
	
	private LocalDate postedDate;

	private int noOfJobPositions;

	private double salary;

	private JobType jobType;
	
	 private List<Long> skillIds;
}

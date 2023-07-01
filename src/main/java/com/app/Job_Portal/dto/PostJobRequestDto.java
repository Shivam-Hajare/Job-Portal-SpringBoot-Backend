package com.app.Job_Portal.dto;

import java.time.LocalDate;
import java.util.List;

import com.app.Job_Portal.entities.JobType;
import com.app.Job_Portal.entities.Skill;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Setter
@Getter
@ToString
public class PostJobRequestDto {

	private long recruiterId;
	
	private String jobTitle;

	private String jobDescription;

	private LocalDate deadLineDate;

	private int noOfJobPositions;

	private double salary;

	private JobType jobType;
	
	 private List<Skill> skills;
}

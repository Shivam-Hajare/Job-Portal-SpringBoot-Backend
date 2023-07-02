package com.app.Job_Portal.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.app.Job_Portal.entities.JobType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Setter
@Getter
@ToString
public class PostJobRequestDto {

	
	private long recruiterId;
	
	@NotBlank(message = "jobTitle can't be blank")
	private String jobTitle;
	@NotBlank(message = "jobDescription can't be blank")
	private String jobDescription;

	private LocalDate deadLineDate;

	private int noOfJobPositions;

	private double salary;

	private JobType jobType;
	
	private List<Long> skillIds;
}

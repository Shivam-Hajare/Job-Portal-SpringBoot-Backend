package com.app.Job_Portal.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.app.Job_Portal.entities.JobType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Setter
@Getter
@ToString
public class PostJobRequestDto {

	
	private long recruiterId;
	
	@NotNull
	@NotBlank(message = "Job Title can't be blank")
	@Size(max =20,message ="Maximum length of title is 20 character")
	private String jobTitle;
	
	@NotNull
	@NotBlank(message = "Job Description can't be blank")
	@Size(max =200,message ="Description should not be greater than 200 character")
	private String jobDescription;

	
	private LocalDate deadLineDate;

	@Min(value = 1, message = "Atleast 1 vacancy needed")
	private int noOfJobPositions;

	@Min(value = 10000, message = "Salary must be at least 10000")
    @Max(value = 1000000, message = "Salary can't exceed 1000000")
	private double salary;

	
	private JobType jobType;
	
	@NotEmpty(message = "Add skills")
	private List<Long> skillIds;
}

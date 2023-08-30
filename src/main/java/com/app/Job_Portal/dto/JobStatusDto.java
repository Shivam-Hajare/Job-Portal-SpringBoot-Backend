package com.app.Job_Portal.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JobStatusDto {

	@NotNull
	@NotBlank(message = "Job status can't be blank")
	@Size(max =10,message ="Maximum length of job status 10 character")
	private String jobStatus;
}

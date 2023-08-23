package com.app.Job_Portal.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RecruiterRequestDto {

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Long recruiterId;
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNo;
    private String recruiterBio;
}

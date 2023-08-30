package com.app.Job_Portal.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
    @Email
	private String email;
	@NotNull
    @NotBlank(message="Please enter your first name")
    private String firstName;
	@NotNull
    @NotBlank(message="Please enter your last name")
    private String lastName;
    @NotNull
    @NotBlank(message="Please enter your phone number")
    private String phoneNo;
    @NotNull
    @NotBlank(message="Please enter recruiter bio")
    private String recruiterBio;
    
    @NotNull
    @NotBlank(message="Please enter your recruiter company name")
    private String companyName;
}

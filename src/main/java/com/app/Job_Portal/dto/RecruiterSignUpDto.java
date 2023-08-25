package com.app.Job_Portal.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class RecruiterSignUpDto {
	
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
    
    @NotNull
    @NotBlank(message="Please enter correct password")
    private String password;
    
    private String role;
}

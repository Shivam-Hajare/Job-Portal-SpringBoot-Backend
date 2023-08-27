package com.app.Job_Portal.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    @Email(message = "Invalid email")
	private String email;
	
	@NotNull
    @NotBlank(message="Please enter your first name")
	@Size(max =20,message ="First Name should not be greater than 20 character")
    private String firstName;
	
	@NotNull
    @NotBlank(message="Please enter your last name")
	@Size(max =20,message ="Last Name should not be greater than 20 character")
    private String lastName;
    
	@NotNull
    @NotBlank(message="Please enter your phone number")
    private String phoneNo;
    
    @NotNull
    @NotBlank(message="Please enter recruiter bio")
    @Size(max =200,message ="Bio should not be greater than 200 character")
    private String recruiterBio;
    
    @NotNull
    @NotBlank(message="Please enter your recruiter company name")
    @Size(max =20,message ="Company Name should not be greater than 20 character")
    private String companyName;
}

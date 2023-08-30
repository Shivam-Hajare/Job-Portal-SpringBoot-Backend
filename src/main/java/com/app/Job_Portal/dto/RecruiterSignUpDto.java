package com.app.Job_Portal.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class RecruiterSignUpDto {
	
    @Email(message = "Invalid Email")
    @NotNull
    @NotBlank(message="Please enter your Email Id")
    @Size(max =20,message ="Email should not be greater than 20 character")
	private String email;
	
    @NotNull
    @NotBlank(message="Please enter your first name")
    @Size(max =20,message ="First Name should not be greater than 20 character")
    private String firstName;
	
    @NotNull
    @NotBlank(message="Please enter your last name")
    @Size(max =20,message ="Last name should not be greater than 20 character")
    private String lastName;
   
    @NotNull
    @NotBlank(message="Please enter your phone number")
    private String phoneNo;
    
    @NotNull
    @NotBlank(message="Please enter recruiter bio")
    @Size(max =200,message ="Bio should not be greater than 200 character")
    private String recruiterBio;
    
    @NotNull
    @NotBlank(message="Please enter recruiter company name")
    @Size(max =100,message ="Maximum length 100 character")
    private String companyName;
    
    @NotNull
    @NotBlank(message="Please enter correct password")
    @Size(max =10,min =4,message ="Password size must be within 4 to 10 character")
    private String password;
    
    @NotNull
    @NotBlank(message="Please enter role")
    @Size(max =20,message ="Maximum length 20 character")
    private String role;
}

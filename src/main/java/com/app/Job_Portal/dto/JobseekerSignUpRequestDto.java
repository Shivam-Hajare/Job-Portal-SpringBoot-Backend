package com.app.Job_Portal.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobseekerSignUpRequestDto {

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

	
	@Max(value = 40, message = "Experience can't be exceed than 40")
	@Min(value = 0, message = "Experience can't be less than 0")
    private int yearOfExperience;

    @NotNull
    @NotBlank(message="Please enter correct password")
    @Size(max =10,min =4,message ="Password size must be within 4 to 10 character")
    private String password;
}

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
public class SignInRequestDto {

	@NotNull
    @NotBlank(message="Please enter your userName")
	@Size(max =20,message ="UserName should not be greater than 20 character")
	private String userName;
	
	@NotNull
    @NotBlank(message="Please enter your password")
	@Size(max =10,min =4,message ="Password size must be within 4 to 10 character")
	private String password;
	
}

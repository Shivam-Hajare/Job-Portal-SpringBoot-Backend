package com.app.Job_Portal.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SignInRequestDto {

	@NotNull
    @NotBlank(message="Please enter your userName")
	private String userName;
	
	@NotNull
    @NotBlank(message="Please enter your password")
	private String password;
	
}

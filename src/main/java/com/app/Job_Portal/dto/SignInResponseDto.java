package com.app.Job_Portal.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SignInResponseDto {

	private Long userId;
	
	private String email;
	
	private String role;
	
	private Long jobSeekerId;
	
	private Long recruiterId;
	
	
	
	
}

package com.app.Job_Portal.service;

import java.util.List;

import com.app.Job_Portal.dto.SignInRequestDto;
import com.app.Job_Portal.dto.SignInResponseDto;

public interface SignInService {

	SignInResponseDto authenticationOfUser(SignInRequestDto signInDto);
}

package com.app.Job_Portal.dto;

import java.time.LocalDate;
import java.util.List;

import com.app.Job_Portal.entities.ApplicationUser;
import com.app.Job_Portal.entities.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO {
//    private ApplicationUser user;
//    private String jwt;
//    private String userType; // "jobseeker" or "recruiter"
//    public LoginResponseDTO(){
//        super();
//    }

    private String jwt;
    private Integer userId;
    private Long jobSeekerId;
    private Long recruiterId;
    private String userType;
}

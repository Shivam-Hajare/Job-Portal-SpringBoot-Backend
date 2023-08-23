package com.app.Job_Portal.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class JobSeekerRequestDto {

    private String email;

    private String firstName;

    private String lastName;

    private int yearOfExperience;


    private List<EducationalDetailsDto> eduInfo;

    private List<SkillDto> skills;
}

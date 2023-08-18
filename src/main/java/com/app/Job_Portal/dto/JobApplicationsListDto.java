package com.app.Job_Portal.dto;

import java.time.LocalDate;
import java.util.List;

import com.app.Job_Portal.entities.Skill;
import com.app.Job_Portal.entities.Status;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JobApplicationsListDto {
	private Long jobSeekerId;
    private String firstName;
    private String lastName;
    private List<SkillDto> skills;
    private byte[] resumeFile;
    private LocalDate appliedDate;
    private Status status;
}

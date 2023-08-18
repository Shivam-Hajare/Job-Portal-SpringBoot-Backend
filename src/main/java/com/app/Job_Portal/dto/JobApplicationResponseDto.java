package com.app.Job_Portal.dto;

import com.app.Job_Portal.entities.JobType;
import com.app.Job_Portal.entities.Skill;
import com.app.Job_Portal.entities.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class JobApplicationResponseDto {

    private Long applicationId;
    private Long jobId;


    private LocalDate appliedDate;

    private Status status;

    private String jobTitle;

    private String jobDescription;
    private LocalDate postedDate;
    private LocalDate deadLineDate;
    private int noOfJobPositions;
    private double salary;
    private JobType jobType;
    private String postedBy;
    private List<String> skillsForJob = new ArrayList<>();

    public void setSkillsForJob(List<Skill> allSkills) {
        allSkills.forEach(skill -> skillsForJob.add(skill.getName()));
    }

}

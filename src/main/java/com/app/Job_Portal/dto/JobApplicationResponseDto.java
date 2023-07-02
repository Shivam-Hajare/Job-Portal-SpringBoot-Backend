package com.app.Job_Portal.dto;

import com.app.Job_Portal.entities.JobType;
import com.app.Job_Portal.entities.Recruiter;
import com.app.Job_Portal.entities.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class JobApplicationResponseDto {

    private Long applicationId;
    private Long jobListingId;


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
    private List<String> skilsForJob;

}

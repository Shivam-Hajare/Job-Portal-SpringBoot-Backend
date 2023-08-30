package com.app.Job_Portal.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "job")
@Setter
@Getter
@AllArgsConstructor
//@ToString
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private Long jobId;
    
    @Column(nullable = false)
    private String jobTitle;

    @Column(nullable = false)
    private String jobDescription;

    @Column(nullable = false)
    private LocalDate postedDate;
    
    @Column(nullable = false)
    private LocalDate deadLineDate;
    
    @Column(nullable = false)
    private int noOfJobPositions;

    @Column(nullable = false)
    private double salary;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)

    private JobType jobType;
    
    @ManyToOne
    @JoinColumn(name = "recruiterId" )
    private Recruiter postedBy;

    @ManyToMany
    @JoinTable(
        name = "job_skills",
        joinColumns = @JoinColumn(name = "job_id"),
        inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private List<Skill> skills;
    
    @OneToMany(mappedBy = "job")
    private List<JobApplication> applications;

    // Constructors, getters, and setters

    public Job() {
        // Default constructor
    }

	@Override
	public String toString() {
		return "JobListing [jobListingId=" + jobId + ", jobTitle=" + jobTitle + ", jobDescription="
				+ jobDescription + ", postedDate=" + postedDate + ", postedBy=" + postedBy + "]";
	}





}

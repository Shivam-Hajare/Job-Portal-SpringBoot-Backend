package com.app.Job_Portal.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "job_applications")
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private Long applicationId;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private JobListing job;

    @ManyToOne
    @JoinColumn(name = "job_seeker_id")
    private JobSeeker jobSeeker;

    @Column(nullable = false)
    private LocalDate appliedDate;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    // Constructors, getters, and setters

    public JobApplication() {
        // Default constructor
    }

    public JobApplication(JobListing job, JobSeeker jobSeeker, LocalDate appliedDate) {
        this.job = job;
        this.jobSeeker = jobSeeker;
        this.appliedDate = appliedDate;
    }

    // Getters and setters for all properties
    // ...

    // Other job application properties, getters, and setters
}


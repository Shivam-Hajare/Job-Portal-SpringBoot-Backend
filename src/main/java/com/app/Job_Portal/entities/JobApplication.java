package com.app.Job_Portal.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
//@ToString
@Table(name = "job_applications")
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private Long applicationId;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @ManyToOne
    @JoinColumn(name = "job_seeker_id")
    private JobSeeker jobSeeker;

    @Column(nullable = false)
    private LocalDate appliedDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    // Constructors, getters, and setters


    public JobApplication(Job job, JobSeeker jobSeeker, LocalDate appliedDate) {
        this.job = job;
        this.jobSeeker = jobSeeker;
        this.appliedDate = appliedDate;
    }

    // Getters and setters for all properties
    // ...

    // Other job application properties, getters, and setters
}


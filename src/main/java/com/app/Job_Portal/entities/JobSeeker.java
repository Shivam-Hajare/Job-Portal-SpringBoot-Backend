package com.app.Job_Portal.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "job_seekers")
@Getter
@Setter
@AllArgsConstructor
@ToString(exclude = "password")
@NoArgsConstructor
public class JobSeeker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_seeker_id")
    private Long jobSeekerId;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    @ColumnDefault("0")
    private int yearOfExperience;

    @OneToMany(mappedBy = "jobSeeker")
    private List<JobApplication> jobApplications = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "jobseeker_id")
    private List<EducationalDetails> eduInfo = new ArrayList<>();
    
    @ManyToMany
    @JoinTable(
        name = "job_seeker_skills",
        joinColumns = @JoinColumn(name = "job_seeker_id"),
        inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private List<Skill> skills = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    public void submitAnApplication(JobApplication application, Job job) {
        this.jobApplications.add(application);
        job.getApplications().add(application);
        application.setJobSeeker(this);
    }

    public void withDrawAnApplication(JobApplication application, Job job) {
        this.jobApplications.remove(application);
        job.getApplications().remove(application);
        application.setJobSeeker(null);
    }

}


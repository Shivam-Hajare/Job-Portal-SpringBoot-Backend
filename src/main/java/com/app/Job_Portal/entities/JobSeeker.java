
package com.app.Job_Portal.entities;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

//    @Column(nullable = false)
//    private String password;
    
    @Column(nullable = false)
    @ColumnDefault("0")
    private int yearOfExperience;

    @OneToMany(mappedBy = "jobSeeker", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<JobApplication> jobApplications = new ArrayList<>();


    @OneToMany(mappedBy = "jobSeeker",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<EducationalDetails> eduInfo = new ArrayList<>();
    
    @ManyToMany
    @JoinTable(
        name = "job_seeker_skills",
        joinColumns = @JoinColumn(name = "job_seeker_id"),
        inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private List<Skill> skills = new ArrayList<>();


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


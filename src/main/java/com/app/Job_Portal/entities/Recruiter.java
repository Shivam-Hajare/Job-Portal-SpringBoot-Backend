package com.app.Job_Portal.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "recruiters")
@Getter
@Setter
@AllArgsConstructor
@ToString(exclude = {"password","jobListings"})
public class Recruiter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruiter_id")
    private Long recruiterId;

    @Column(unique = true, nullable = false)
    private String userName;

    @Column(nullable = false)
    private String firstName;

    private String lastName;

    @Column(nullable = false)
    private String password;

//    @ManyToMany
//    @JoinTable(
//        name = "recruiter_skills",
//        joinColumns = @JoinColumn(name = "recruiter_id"),
//        inverseJoinColumns = @JoinColumn(name = "skill_id")
//    )
//    private List<Skill> skills;
    
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @OneToMany(mappedBy = "postedBy",fetch =FetchType.EAGER )
    private List<JobListing> jobListings;

    // Constructors, getters, and setters

    public Recruiter() {
        // Default constructor
    }

   

    // Getters and setters for all properties
    // ...

    // Other recruiter properties, getters, and setters
}

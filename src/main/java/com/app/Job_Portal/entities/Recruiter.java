package com.app.Job_Portal.entities;

import java.util.List;

import javax.persistence.CascadeType;
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
@ToString(exclude = {"jobListings"})
public class Recruiter {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruiter_id")
    private Long recruiterId;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String firstName;
    
    @Column(nullable = false)
    private String lastName;
    
    @Column(nullable = false)
    private String phoneNo;
    
    @Column(nullable = false)
    private String recruiterBio;
    
    @Column(nullable = false)
    private String companyName;

    @OneToMany(mappedBy = "postedBy",fetch =FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true )
    private List<Job> jobListings;

    
    //    @OneToMany(mappedBy = "jobSeeker",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)

    // Constructors, getters, and setters

    public Recruiter() {
        // Default constructor
    }

   

    // Getters and setters for all properties
    // ...

    // Other recruiter properties, getters, and setters
}

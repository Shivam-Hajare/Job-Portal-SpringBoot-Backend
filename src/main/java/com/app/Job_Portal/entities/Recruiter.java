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

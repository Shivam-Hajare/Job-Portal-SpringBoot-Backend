package com.app.Job_Portal.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "skills")@Getter
@Setter
@AllArgsConstructor
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id")
    private Long skillId;

    @Column(nullable = false)
    private String name;

    private String description;

    @ManyToMany(mappedBy = "skills",cascade = CascadeType.ALL)
    private List<JobListing> jobListings;

    @ManyToMany(mappedBy = "skills",cascade = CascadeType.ALL)
    private List<JobSeeker> jobSeekers;
    // Constructors, getters, and setters

    public Skill() {
        // Default constructor
    }




    // Getters and setters for all properties
    // ...

    // Other skill properties, getters, and setters
}

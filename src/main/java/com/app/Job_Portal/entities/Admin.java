package com.app.Job_Portal.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "admin")
@Getter
@Setter
@AllArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Long adminId;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String firstName;
    
    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "admin")
    private List<JobSeeker> jobSeekers;

    @OneToMany(mappedBy = "admin")
    private List<Recruiter> recruiters;

    public Admin() {
        // Default constructor
    }

    public Admin(Long id) {
        this.adminId = id;
    }

    public Admin(long l, String mail, String firstName, String lastName, String password) {

        this.adminId = (long)1;
        this.email  = mail;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }
}

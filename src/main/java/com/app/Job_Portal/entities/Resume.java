package com.app.Job_Portal.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "resume")
@Getter
@Setter
@AllArgsConstructor
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resume_id")
    private Long resumeId;


    @OneToOne
    @JoinColumn(name = "jobseeker_id")
    private JobSeeker jobseeker;

 
    @Column(name = "resume_file")
    private byte[] resumeFile;

    public Resume() {
    }

}

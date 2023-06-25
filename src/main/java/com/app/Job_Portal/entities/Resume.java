package com.app.Job_Portal.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

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
    @JoinColumn(name="jobseeker_id")
    private JobSeeker jobseeker;

    @Column(name = "resume_file")
    private byte[] resumeFile;

    public Resume() {
    }

}

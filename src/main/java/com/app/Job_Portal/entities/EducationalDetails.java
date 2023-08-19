package com.app.Job_Portal.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "educational_details")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class EducationalDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "educational_id")
    private Long educationalId;


    @Column(nullable = false)
    private String qualification;

    @Column(nullable = false)
    private String institute;

    @Column(nullable = false)
    private LocalDate admissionDate;

    @Column(nullable = false)
    private LocalDate completionDate;

    @Column(nullable = false)
    private double percentage;

    @ManyToOne
    @JoinColumn(name = "jobseeker_id")
    private JobSeeker jobSeeker;

}

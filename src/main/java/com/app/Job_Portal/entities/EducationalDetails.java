package com.app.Job_Portal.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "educational_details")
@Getter
@Setter
@AllArgsConstructor
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
	private JobSeeker jobseeker;
	
	
	
}

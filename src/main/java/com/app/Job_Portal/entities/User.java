package com.app.Job_Portal.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", nullable = false)
	private Long userId;
	
	@Email
	@Column(unique = true)
	private String email;

	@Column(nullable = false)
	// @JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	@Column(nullable = false)
	private String role;

	@OneToOne
	@JoinColumn(name = "admin_id")
	private Admin admin;

	@OneToOne
	@JoinColumn(name = "jobSeeker_id")
	private JobSeeker jobSeeker;

	@OneToOne
	@JoinColumn(name = "recruiter_id")
	private Recruiter recruiter;

}

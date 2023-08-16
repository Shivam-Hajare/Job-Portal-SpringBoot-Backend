package com.app.Job_Portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.Job_Portal.entities.JobApplication;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {

}

package com.app.Job_Portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.Job_Portal.entities.JobListing;

public interface JobRepository extends JpaRepository<JobListing, Long> {
          
}

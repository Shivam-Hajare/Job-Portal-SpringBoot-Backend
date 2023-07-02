package com.app.Job_Portal.repository;

import com.app.Job_Portal.entities.JobListing;
import com.app.Job_Portal.entities.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobListingRepository extends JpaRepository<JobListing, Long> {
}

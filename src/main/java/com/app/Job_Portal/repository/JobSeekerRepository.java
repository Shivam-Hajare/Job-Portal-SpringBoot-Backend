package com.app.Job_Portal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.Job_Portal.entities.JobSeeker;

public interface JobSeekerRepository extends JpaRepository<JobSeeker, Long> {
    Optional<JobSeeker> findByEmail(String email);

}

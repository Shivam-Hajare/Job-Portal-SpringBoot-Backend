package com.app.Job_Portal.repository;

import com.app.Job_Portal.entities.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;

import com.app.Job_Portal.entities.Admin;

import java.util.Optional;

public interface JobSeekerRepository extends JpaRepository<JobSeeker, Long>{

    Optional<JobSeeker> findById(Long id);
}

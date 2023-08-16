package com.app.Job_Portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.Job_Portal.entities.Job;

public interface JobRepository extends JpaRepository<Job, Long> {
          
}

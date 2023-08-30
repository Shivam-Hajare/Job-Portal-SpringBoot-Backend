package com.app.Job_Portal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.Job_Portal.entities.Resume;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
    @Query("SELECT r FROM Resume r WHERE r.jobseeker.id = :jobseekerId")
    Optional<Resume> findResumeByJobSeekerId(Long jobseekerId);
}

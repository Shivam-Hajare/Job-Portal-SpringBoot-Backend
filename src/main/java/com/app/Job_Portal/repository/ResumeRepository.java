package com.app.Job_Portal.repository;

import com.app.Job_Portal.entities.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
    @Query("SELECT r FROM Resume r WHERE r.jobseeker.id = :jobseekerId")
    Optional<Resume> findResumeByJobSeekerId(Long jobseekerId);
}

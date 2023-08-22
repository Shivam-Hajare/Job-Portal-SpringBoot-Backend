package com.app.Job_Portal.repository;

import com.app.Job_Portal.entities.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
    @Query("SELECT r.resumeFile FROM Resume r WHERE r.jobseeker.id = :jobseekerId")
    byte[] findResumeByJobSeekerId(Long jobseekerId);
}

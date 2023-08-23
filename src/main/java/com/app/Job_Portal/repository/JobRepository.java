package com.app.Job_Portal.repository;

import com.app.Job_Portal.entities.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import com.app.Job_Portal.entities.Job;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    @Query("SELECT j.skills FROM Job j WHERE j.jobId = :jobId")
    List<Skill> findSkillsByJobId(@Param("jobId") Long jobId);
}

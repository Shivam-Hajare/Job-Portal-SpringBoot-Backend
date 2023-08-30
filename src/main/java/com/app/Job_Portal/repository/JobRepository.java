package com.app.Job_Portal.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.Job_Portal.entities.Job;
import com.app.Job_Portal.entities.Skill;

public interface JobRepository extends JpaRepository<Job, Long> {
  @Query("SELECT j.skills FROM Job j WHERE j.jobId = :jobId")
    List<Skill> findSkillsByJobId(@Param("jobId") Long jobId);

}

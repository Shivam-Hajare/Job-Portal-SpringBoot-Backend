package com.app.Job_Portal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.Job_Portal.entities.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long>{
	@Query("SELECT s FROM Skill s JOIN s.jobSeekers js WHERE js.jobSeekerId = :jobSeekerId")
    List<Skill> findAllSkillsByJobSeekerId(Long jobSeekerId);
}

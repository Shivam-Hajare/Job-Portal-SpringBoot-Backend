package com.app.Job_Portal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.Job_Portal.entities.Skill;


public interface SkillRepository extends JpaRepository<Skill, Long>{
	@Query("SELECT s FROM Skill s JOIN s.jobSeekers js WHERE js.jobSeekerId = :jobSeekerId")
    List<Skill> findAllSkillsByJobSeekerId(Long jobSeekerId);

    List<Skill> findAllByNameIn(List<String> list);

    Optional<Skill> findByName(String name);
    @Query("SELECT s.name FROM Skill s JOIN s.jobListings jl WHERE jl.jobId = :jobId")
    List<String> findSkillNamesByJobId(@Param("jobId") Long jobId);
    
  //  @Query("SELECT s.name FROM Skill s JOIN s.jobListings jl WHERE jl.jobId = :jobId")
  //  List<String> findSkillForByJobId(@Param("jobId") Long jobId);

    Optional<Skill> findBySkillId(Long skillId);
}

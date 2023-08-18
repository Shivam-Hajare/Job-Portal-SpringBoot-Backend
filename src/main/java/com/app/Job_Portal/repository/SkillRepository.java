package com.app.Job_Portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.Job_Portal.entities.Skill;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skill, Long>{
    List<Skill> findAllByNameIn(List<String> list);
}

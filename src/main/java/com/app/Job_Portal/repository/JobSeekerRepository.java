package com.app.Job_Portal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.Job_Portal.entities.Admin;
import com.app.Job_Portal.entities.JobSeeker;
import com.app.Job_Portal.entities.Recruiter;
import com.app.Job_Portal.entities.Skill;

public interface JobSeekerRepository extends JpaRepository<JobSeeker, Long>{

	


}

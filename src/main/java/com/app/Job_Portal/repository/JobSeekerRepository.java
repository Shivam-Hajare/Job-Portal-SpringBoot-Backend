package com.app.Job_Portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.Job_Portal.entities.Admin;

public interface JobSeekerRepository extends JpaRepository<Admin, Long>{

}

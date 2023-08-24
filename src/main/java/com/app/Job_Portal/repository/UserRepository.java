package com.app.Job_Portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.Job_Portal.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}

package com.projectmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectmanager.domain.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{

}

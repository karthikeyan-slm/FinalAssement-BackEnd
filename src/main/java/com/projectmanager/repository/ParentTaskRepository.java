package com.projectmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectmanager.domain.ParentTask;


public interface ParentTaskRepository extends JpaRepository<ParentTask, Long>{

}

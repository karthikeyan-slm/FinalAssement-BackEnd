package com.projectmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.projectmanager.domain.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{
	
	@Query(value="select t.* from task t,project p where p.project_id = t.project_project_id and p.project_id = ?1", nativeQuery=true)
	public  List<Task> findByProjectId(Long projectId);

}

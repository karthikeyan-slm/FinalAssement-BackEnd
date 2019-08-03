package com.projectmanager.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectmanager.domain.Project;
import com.projectmanager.domain.User;
import com.projectmanager.models.ProjectModel;
import com.projectmanager.repository.ProjectRepository;
import com.projectmanager.repository.UserRepository;

@Service
@Transactional
public class ProjectService {

	private final Logger logger = LoggerFactory.getLogger(ProjectService.class);

	@Autowired
	private ProjectRepository projectRepo;
	
	@Autowired
	private UserService userService;

	public Project addProject(ProjectModel projectModel) {
		logger.info("Entered Method Name: addProject");
		Project projectObj = convertUItoDomain(projectModel);
		if( null != projectModel.getUserId()) {
			User user = userService.findUserById(Long.parseLong(projectModel.getUserId()));
			if( user !=  null) {
				projectObj.setUser(user);
			}
		}
		Project i = projectRepo.save(projectObj);
		return i;
	}
	public Project findProjectById(Long Id) {
		logger.info("Entered Method Name: findProjectById ");
		Optional<Project> projectIfExist = projectRepo.findById(Id);
		if (projectIfExist != null)
			return (Project) projectIfExist.get();
		else
			return null;
	}
	public List<Project> getAllProjects() {
		logger.info("Entered Method Name: getAllProjects ");
		return (List<Project>)projectRepo.findAll();
	}

	public Project deleteProject(ProjectModel projectModel) {
		logger.info("Entered Method Name: deleteProject ");
		Project projectObj = convertUItoDomain(projectModel);
		projectRepo.delete(projectObj);
		return projectObj;
	}
	
	private Project convertUItoDomain(ProjectModel projectModel) {
		Project project = new Project();
		if(null != projectModel) {
			if(null != projectModel.getProjectId() && !projectModel.getProjectId().isEmpty()) {
				project.setProjectId(Long.parseLong(projectModel.getProjectId()));
			}
			project.setProject(projectModel.getProject());
			project.setPriority(projectModel.getPriority());
			project.setStartDate(projectModel.getStartDate());
			project.setEndDate(projectModel.getEndDate());
		}
		return project;
		
	}

}

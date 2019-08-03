package com.projectmanager.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectmanager.domain.Project;
import com.projectmanager.models.ProjectModel;
import com.projectmanager.service.ProjectService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/pmapp/projectAction")
public class ProjectController {

	private final Logger logger = LoggerFactory.getLogger(ProjectController.class);
	
	@Autowired
	private ProjectService projectService;

	@PostMapping("/addProject")
	public Project addProject(@RequestBody ProjectModel projectModel) throws Exception
	{
		logger.info(" Entered addProject Method in ProjectController");
		logger.info(" Project Details to be added:" + projectModel.toString());
		Project project = projectService.addProject(projectModel);
		return project;
	}

	@RequestMapping("/getAllProjects")
	public List<Project> getAllProjects() throws Exception
	{
		List<Project> projectList = null;
		logger.info(" Entered getAllProjects Method in ProjectController");
		projectList=projectService.getAllProjects();
		return  projectList;
	}
	
	
	@RequestMapping("/deleteProject")
	public Project deleteProject(@RequestBody ProjectModel projectModel) throws Exception
	{
		Project returned = null;
		logger.info(" Entered deleteProject Method in ProjectController");
		returned=projectService.deleteProject(projectModel);
		return  returned;
	}
	
	
	@RequestMapping("/getProjectById/{projectId}")
	public Project getProjectById(@PathVariable(name="projectId")String projectId ) throws Exception
	{
		logger.info(" Entered getProjectById Method in ProjectController");
		logger.info(" Project Id Searched for: " + projectId);
		Project project = null;
		project = projectService.findProjectById(Long.parseLong(projectId));
		return project;
		
	}

	@PostMapping("/updateProject")
	private Project updateProject(@RequestBody ProjectModel projectModel) throws Exception{

		logger.info(" Entered updateProject Method in ProjectController");
		Project project = projectService.addProject(projectModel);
		return  project;
	}


}

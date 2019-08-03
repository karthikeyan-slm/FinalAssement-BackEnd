package com.projectmanager.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectmanager.domain.ParentTask;
import com.projectmanager.service.ParentTaskService;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/pmapp/parentAction")
public class ParentTaskController {
	
private final Logger logger = LoggerFactory.getLogger(ProjectController.class);
	
	@Autowired
	private ParentTaskService parentService;
	
	@PostMapping("/addParentTask")
	public ParentTask addParentTask(@RequestBody ParentTask parentTask) throws Exception
	{
		logger.info(" Entered addParentTask Method in ParentTaskController");
		logger.info("Project Details to be added:" + parentTask.toString());
		parentService.addParentTask(parentTask);
		return parentTask;
	}
	
	@RequestMapping("/getAllParentTasks")
	public List<ParentTask> getAllParentTasks() throws Exception
	{
		List<ParentTask> parentList = null;
		logger.info(" Entered getAllParentTasks Method in ParentTaskController");
		parentList=parentService.getAllParentTasks();
		return  parentList;
	}

}

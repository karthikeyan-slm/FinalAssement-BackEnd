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

import com.projectmanager.domain.Task;
import com.projectmanager.models.TaskModel;
import com.projectmanager.service.TaskService;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/pmapp/taskAction")
public class TaskController {

private final Logger logger = LoggerFactory.getLogger(TaskController.class);
	
	@Autowired
	private TaskService taskService;
		
		
	@PostMapping("/addTask")
	public Task addTask(@RequestBody TaskModel taskModel) throws Exception
	{
		logger.info(" Entered addTask Method in TaskController");
		logger.info("Task Details to be added:" + taskModel.toString());
		Task task = taskService.addTask(taskModel);
		return task;
	}
	

	@RequestMapping("/getAllTasks")
	public List<Task> getAllTasks() throws Exception
	{
		List<Task> taskList = null;
		logger.info(" Entered getAllTask Method in TaskController");
		taskList=taskService.getAllTasks();
		return  taskList;
	}
	
	@RequestMapping("/deleteTask")
	public Task deleteTask(@RequestBody TaskModel taskModel)throws Exception
	{
		logger.info(" Entered deleteTask Method in TaskController");
		Task taskObj=taskService.deleteTask(taskModel);
		return  taskObj;
	}
	
	
	@RequestMapping("/getTaskById/{taskId}")
	public  List<TaskModel> getTaskById(@PathVariable(name="taskId")String taskId ) throws Exception
	{
		logger.info(" Entered getTaskById Method in TaskController");
		logger.info("Task Id Searched for: " + taskId);
		List<TaskModel> taskList = null;
		taskList = taskService.findTaskByProjectId(Long.parseLong(taskId));
		return taskList;
	}
	
	@PostMapping("/updateTask")
	private Task updateTask(@RequestBody TaskModel taskModel) throws Exception{

		logger.info(" Entered updateTask Method in TaskController");
		logger.info(" Task Details to be update:" + taskModel.toString());
		Task task = taskService.addTask(taskModel);
		return  task;
	}
}

package com.projectmanager.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectmanager.domain.ParentTask;
import com.projectmanager.domain.Project;
import com.projectmanager.domain.Task;
import com.projectmanager.domain.User;
import com.projectmanager.models.TaskModel;
import com.projectmanager.repository.TaskRepository;

@Service
@Transactional
public class TaskService {

	private final Logger logger = LoggerFactory.getLogger(TaskService.class);

	@Autowired
	private TaskRepository taskRepo;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private ParentTaskService parentTaskService;
	
	
	public Task addTask(TaskModel taskModel) {
		logger.info("Entered Method Name: addTask");
		
		Task taskObj = convertUItoDomain(taskModel);
		if( null != taskModel.getUserId() ) {
			User user = userService.findUserById(Long.parseLong(taskModel.getUserId()));
			if( user !=  null) {
				taskObj.setUser(user);
			}
		}
		if( null != taskModel.getProjectId() ) {
			Project project = projectService.findProjectById(Long.parseLong(taskModel.getProjectId()));
			if( project !=  null) {
				taskObj.setProject(project);
			}
		}
		
		if( null != taskModel.getParentTaskId()) {
			ParentTask parentTask = parentTaskService.findParentTaskById(Long.parseLong(taskModel.getParentTaskId()));
			if( parentTask !=  null) {
				taskObj.setParentTask(parentTask);
			}
		}
		Task i = taskRepo.save(taskObj);
		return i;
	}

	public  List<TaskModel> findTaskByProjectId(Long Id) {
		logger.info("Entered Method Name: findTaskById ");
		List<TaskModel> taskList = new ArrayList<TaskModel>();
		 List<Task> taskIfExist = taskRepo.findByProjectId(Id);
		if (taskIfExist != null && taskIfExist.size()>0) {
			for(Task taskObj : taskIfExist) {
				TaskModel taskModel = new TaskModel();
				convertDomaintoUI(taskObj,taskModel);
				taskList.add(taskModel);
			}
			return taskList;
		
		}else {
			return null;
		}
	}

	public List<Task> getAllTasks() {
		logger.info("Entered Method Name: getAllTasks ");
		return (List<Task>) taskRepo.findAll();
	}

	public Task deleteTask(TaskModel taskModel) {
		logger.info("Entered Method Name: deleteTask ");
		Task task = convertUItoDomain(taskModel);
		task.setProject(null);
		task.setUser(null);
		taskRepo.delete(task);
		return task;
	}
	
	private Task convertUItoDomain(TaskModel taskModel) {
		Task taskObj = new Task();
		if(null != taskModel) {
			if(null != taskModel.getTaskId() && !taskModel.getTaskId().isEmpty()) {
				taskObj.setTaskId(Long.parseLong(taskModel.getTaskId()));
			}
			taskObj.setTask(taskModel.getTask());
			taskObj.setPriority(taskModel.getPriority());
			taskObj.setStartDate(taskModel.getStartDate());
			taskObj.setEndDate(taskModel.getEndDate());
			taskObj.setStatus(taskModel.getStatus());
		}
		
		return taskObj;
	}
	
	private TaskModel convertDomaintoUI(Task taskObj, TaskModel taskModel) {
		if(null != taskObj) {
			taskModel.setTaskId(String.valueOf(taskObj.getTaskId()));
			taskModel.setTask(taskObj.getTask());
			taskModel.setPriority(taskObj.getPriority());
			taskModel.setStartDate(taskObj.getStartDate());
			taskModel.setEndDate(taskObj.getEndDate());
			taskModel.setStatus(taskObj.getStatus());
			if(null != taskObj.getParentTask()) {
				taskModel.setParentTaskId(String.valueOf(taskObj.getParentTask().getParentTaskId()));
				taskModel.setParentTask(taskObj.getParentTask().getParentTask());
				
			}
			if(null != taskObj.getProject()) {
				taskModel.setProjectId(String.valueOf(taskObj.getProject().getProjectId()));
				taskModel.setProject(taskObj.getProject().getProject());
				
			}
			if(null != taskObj.getUser()) {
				taskModel.setUserId(String.valueOf(taskObj.getUser().getUserId()));
				taskModel.setUserName(taskObj.getUser().getFirstName());
				
			}
		}
		
		return taskModel;
	}

}

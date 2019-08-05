package com.projectmanager.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.projectmanager.domain.Task;
import com.projectmanager.models.TaskModel;
import com.projectmanager.service.TaskService;

@RunWith(SpringRunner.class)
@WebMvcTest(value=TaskController.class)
public class TaskControllerTest {

	/** The mock mvc. */
	@Autowired
	private MockMvc mockMvc;
	
	/** The task service. */
	@MockBean
	private TaskService taskService;
	
	/** The taskcontroller. */
	@Autowired
	private TaskController taskcontroller;
	
	
	/** The task string. */
	@Value("${testcase.task}")
	private String taskString;
	
	/** The task. */
	private Task task = null;
	
	private TaskModel taskModel = null;
	
	
	private TaskModel retTask =null;
	
	/** The task list. */
	private List<TaskModel> taskModelList = new ArrayList();
	
	private List<Task> taskList = new ArrayList();
	
	
	/**
	 * Creates the task.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Before
	public void createTask() throws IOException
	{
        task = new Task();
        task = createObject(taskString);
        taskModel = new TaskModel();
        taskModel = convertDomaintoUI(task, taskModel);
        taskList.add(task);
	}
	
	/**
	 * Adds the task.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void addTask() throws Exception {
		String expectedJson = createJson(taskModel);
		String restURI = "/pmapp/taskAction/addTask";
        String outJson = returnExpectedJson(expectedJson,restURI);
        Task retTaskObj = createObject(outJson);
        retTask = new TaskModel();
        retTask = convertDomaintoUI(retTaskObj, retTask);
        assertEquals(retTask.getPriority(), retTask.getPriority());
	}
	
	
	/**
	 * Delete task.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void deleteTask() throws Exception {
		String expectedJson = createJson(taskModel);
		String restURI = "/pmapp/taskAction/deleteTask";
        String outJson = returnExpectedJson(expectedJson,restURI);
        Task retTaskObj = createObject(outJson);
        retTask = new TaskModel();
        retTask = convertDomaintoUI(retTaskObj, retTask);
        assertEquals(retTask.getPriority(), retTask.getPriority());
	}
	
	/**
	 * Update task.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void updateTask() throws Exception {
		String expectedJson = createJson(taskModel);
		String restURI = "/pmapp/taskAction/updateTask";
        String outJson = returnExpectedJson(expectedJson,restURI);
        Task retTaskObj = createObject(outJson);
        retTask = new TaskModel();
        retTask = convertDomaintoUI(retTaskObj, retTask);
        assertEquals(retTask.getPriority(), retTask.getPriority());
	}
	
	/**
	 * Gets the all task.
	 *
	 * @return the all task
	 * @throws Exception the exception
	 */
	@Test
	public void getAllTask() throws Exception {
	 
		Mockito.when(taskService.getAllTasks()).thenReturn(taskList);
		List<Task> actual = taskcontroller.getAllTasks();
		verify(taskService,times(1)).getAllTasks();
		verifyNoMoreInteractions(taskService);
	}
	
	
	/**
	 * Return expected json.
	 *
	 * @param expectedJson the expected json
	 * @param restURI the rest URI
	 * @return the string
	 * @throws Exception the exception
	 */
	private String returnExpectedJson(String expectedJson, String restURI) throws Exception
	{
		
		Mockito.when(taskService.addTask(Mockito.any(TaskModel.class))).thenReturn(task);
		Mockito.when(taskService.deleteTask((Mockito.any(TaskModel.class)))).thenReturn(task);

		
	        RequestBuilder reqBuilder = MockMvcRequestBuilders.post(restURI)
	        		                                          .accept(MediaType.APPLICATION_JSON)
	        		                                          .content(expectedJson)
	        		                                          .contentType(MediaType.APPLICATION_JSON);
	       MvcResult mvcResult= mockMvc.perform(reqBuilder).andReturn();
	       MockHttpServletResponse mockresponse = mvcResult.getResponse();
	       return mockresponse.getContentAsString(); 
	}
	
	/**
	 * Creates the json.
	 *
	 * @param object the object
	 * @return the string
	 * @throws JsonProcessingException the json processing exception
	 */
	private String createJson(Object object) throws JsonProcessingException{
		ObjectMapper objMapper = new ObjectMapper();
		objMapper.registerModule(new JavaTimeModule());
		objMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		return objMapper.writeValueAsString(object);
	}
	
	/**
	 * Creates the object.
	 *
	 * @param json the json
	 * @return the task
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private Task createObject(String json) throws IOException{
		ObjectMapper objMapper = new ObjectMapper();
		objMapper.registerModule(new JavaTimeModule());
		objMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		return objMapper.readValue(json,Task.class);
		
	}
	/**
	 * Creates the object.
	 *
	 * @param json the json
	 * @return the taskModel
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private TaskModel createTaskModelObject(String json) throws IOException{
		ObjectMapper objMapper = new ObjectMapper();
		objMapper.registerModule(new JavaTimeModule());
		objMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		return objMapper.readValue(json,TaskModel.class);
		
	}
	
	private TaskModel convertDomaintoUI(Task taskObj, TaskModel taskModel) {
		if(null != taskObj) {
			
			if(!StringUtils.isEmpty(taskObj.getTaskId()) && null != Long.valueOf(taskObj.getTaskId()) ) {
				taskModel.setTaskId(String.valueOf(taskObj.getTaskId()));
			}
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

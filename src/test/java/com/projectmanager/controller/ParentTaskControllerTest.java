package com.projectmanager.controller;

import static org.junit.Assert.assertEquals;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.projectmanager.domain.ParentTask;
import com.projectmanager.service.ParentTaskService;

@RunWith(SpringRunner.class)
@WebMvcTest(value=ParentTaskController.class)
public class ParentTaskControllerTest {
	
	/** The mock mvc. */
	@Autowired
	private MockMvc mockMvc;
	
	/** The parent service. */
	@MockBean
	private ParentTaskService parentService;
	
	/** The parent task. */
	private ParentTask parentTask = null;
	
	/** The parent task constructor. */
	private ParentTask parentTaskConstructor = null;
	
	/** The parent list. */
	private List<ParentTask> parentList = new ArrayList();
	
	/** The parent controller. */
	@Autowired
	private ParentTaskController parentController;
	
	
	/** The parent string. */
	@Value("${testcase.parentTask}")
	private String parentString;
	
	
	/**
	 * Creates the test data.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Before
	public void createTestData() throws IOException
	{
		parentTask = new ParentTask();
		parentTask = createObject(parentString);
		parentTaskConstructor = new ParentTask(parentTask.getParentTaskId(), parentTask.getParentTask());
		parentList.add(parentTask);
		parentList.add(parentTask);
	}
	
	/**
	 * Adds the parent.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void addParent() throws Exception {
		String expectedJson = createJson(parentTask);
		String restURI = "/pmapp/parentAction/addParentTask";
        String outJson = returnExpectedJson(expectedJson,restURI);
        ParentTask retParentTask = createObject(outJson);
        assertEquals(retParentTask.getParentTask(), parentTask.getParentTask());
	}
	
	/**
	 * Gets the all parent.
	 *
	 * @return the all parent
	 * @throws Exception the exception
	 */
	@Test
	public void getAllParent() throws Exception {
	 
		Mockito.when(parentService.getAllParentTasks()).thenReturn(parentList);
		List<ParentTask> actual = parentController.getAllParentTasks();
		verify(parentService,times(1)).getAllParentTasks();
		verifyNoMoreInteractions(parentService);
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
	 * @return the parent task
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private ParentTask createObject(String json) throws IOException{
		ObjectMapper objMapper = new ObjectMapper();
		objMapper.registerModule(new JavaTimeModule());
		objMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		return objMapper.readValue(json,ParentTask.class);
		
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
		
		Mockito.when(parentService.addParentTask(Mockito.any(ParentTask.class))).thenReturn(parentTask);
		
	        RequestBuilder reqBuilder = MockMvcRequestBuilders.post(restURI)
	        		                                          .accept(MediaType.APPLICATION_JSON)
	        		                                          .content(expectedJson)
	        		                                          .contentType(MediaType.APPLICATION_JSON);
	       MvcResult mvcResult= mockMvc.perform(reqBuilder).andReturn();
	       MockHttpServletResponse mockresponse = mvcResult.getResponse();
	       return mockresponse.getContentAsString(); 
	}


}

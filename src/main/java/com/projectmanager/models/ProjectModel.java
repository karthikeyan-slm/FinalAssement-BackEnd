package com.projectmanager.models;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class ProjectModel {
	
	private String projectId;
	private String project;
	@DateTimeFormat(pattern = "yyyy-MM-dd" )
	private LocalDate startDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd" )	
	private LocalDate endDate;
	private int priority;
	private String userId;
	
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

}

/*
 * 
 */
package com.projectmanager.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class ParentTask {

	/** The parent task id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private long parentTaskId;
	
	/** The parent task. */
	@NotNull
	private String parentTask;
	
	@OneToMany
	@JoinTable(name="TASK_ID",joinColumns=@JoinColumn(name="PARENT_TASK_ID"),
	           inverseJoinColumns = @JoinColumn(name="TASK_ID"))
	private List<Task> task = new ArrayList();
	
	/**
	 * Instantiates a new parent task.
	 */
	public ParentTask() {
	}

	/**
	 * Instantiates a new parent task.
	 *
	 * @param parentTaskId
	 *            the parent task id
	 * @param parentTask
	 *            the parent task
	 */
	public ParentTask(@NotNull long parentTaskId, @NotNull String parentTask) {
		super();
		this.parentTaskId = parentTaskId;
		this.parentTask = parentTask;
	}
	/**
	 * Instantiates a new parent task.
	 *
	 * @param parentTaskId
	 *            the parent task id
	 * @param parentTask
	 *            the parent task
	 */
	public ParentTask(@NotNull long parentTaskId, @NotNull String parentTask,List<Task> task) {
		super();
		this.parentTaskId = parentTaskId;
		this.parentTask = parentTask;
		this.task = task;
	}
	
	
	/**
	 * Gets the parent task id.
	 *
	 * @return the parent task id
	 */
	public long getParentTaskId() {
		return parentTaskId;
	}
	
	
	/**
	 * Sets the parent task id.
	 *
	 * @param parentTaskId
	 *            the new parent task id
	 */
	public void setParentTaskId(long parentTaskId) {
		this.parentTaskId = parentTaskId;
	}
	
	
	/**
	 * Gets the parent task.
	 *
	 * @return the parent task
	 */
	public String getParentTask() {
		return parentTask;
	}
	
	
	/**
	 * Sets the parent task.
	 *
	 * @param parentTask
	 *            the new parent task
	 */
	public void setParentTask(String parentTask) {
		this.parentTask = parentTask;
	}
	
	/**
	 * Gets the task.
	 *
	 * @return the task
	 */
	public List<Task> getTask() {
		return task;
	}

	/**
	 * Sets the task.
	 *
	 * @param task the new task
	 */
	public void setTask(List<Task> task) {
		this.task = task;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ParentTask [parentTaskId=" + parentTaskId + ", parentTask=" + parentTask +"]";
	}
	


	
}

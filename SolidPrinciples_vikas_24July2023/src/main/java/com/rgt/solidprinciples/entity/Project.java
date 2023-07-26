package com.rgt.solidprinciples.entity;

import java.util.ArrayList;
import java.util.List;

public class Project 
{
	private Integer projectId;
	private String name;
	private List<Task> tasks = new ArrayList<>();

	public Project(String name) {
		super();
		this.name = name;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

}

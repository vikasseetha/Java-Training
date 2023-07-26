package com.rgt.solidprinciples.service;

import java.util.List;

import com.rgt.solidprinciples.entity.Project;
import com.rgt.solidprinciples.entity.Task;

public interface ProjectService 
{
	public void createProject(String name);
	
	List<Task>getTasksByProject(Project project);
	
	public void addTaskToProject(Project project ,Task task);	
}

package com.rgt.solidprinciples.service;

import java.time.LocalDate;

import com.rgt.solidprinciples.entity.Priority;
import com.rgt.solidprinciples.entity.Task;

public interface TaskService 
{
	public void createTask(String title, String description, LocalDate dueDate, Priority priority);
	
	public void completeTask(Task task);
       
}

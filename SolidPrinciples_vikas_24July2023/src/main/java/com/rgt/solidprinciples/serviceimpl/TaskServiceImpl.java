package com.rgt.solidprinciples.serviceimpl;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.rgt.solidprinciples.entity.Priority;
import com.rgt.solidprinciples.entity.Task;
import com.rgt.solidprinciples.repository.TaskRepository;
import com.rgt.solidprinciples.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService 
{
    private TaskRepository taskRepository;
     
	public TaskServiceImpl(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@Override
	public void createTask(String title, String description, LocalDate dueDate, Priority priority)
	{
		Task task =new Task(title, description, dueDate, priority);
		taskRepository.addTask(task);
	}

	@Override
	public void completeTask(Task task) 
	{
		task.setCompleted(true);	
	}
}

package com.rgt.solidprinciples.repository;

import java.util.List;

import com.rgt.solidprinciples.entity.Task;

public interface TaskRepository 
{
   public void addTask(Task task);
   
   public List<Task>getAllTask();
   
   List<Task>getTasksByPriority(String priority);
   
   Task getTaskByTitle(String title);  
}

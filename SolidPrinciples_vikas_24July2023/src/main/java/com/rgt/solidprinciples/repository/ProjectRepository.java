package com.rgt.solidprinciples.repository;

import java.util.List;

import com.rgt.solidprinciples.entity.Project;

public interface ProjectRepository 
{
  public void addProject(Project project);
  
  List<Project>getAllProject();
  
  Project getProjectByName(String name);
}

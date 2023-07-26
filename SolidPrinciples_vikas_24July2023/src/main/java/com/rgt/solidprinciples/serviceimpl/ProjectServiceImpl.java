package com.rgt.solidprinciples.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rgt.solidprinciples.entity.Project;
import com.rgt.solidprinciples.entity.Task;
import com.rgt.solidprinciples.repository.ProjectRepository;
import com.rgt.solidprinciples.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService 
{
	private ProjectRepository projectRepository;
	
	public ProjectServiceImpl(ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}

	@Override
	public void createProject(String name) {
		Project project = new Project(name);
		projectRepository.addProject(project);
	}

	@Override
	public List<Task> getTasksByProject(Project project) {
		return project.getTasks();
	}

	@Override
	public void addTaskToProject(Project project, Task task) {
		List<Task> taskProject = project.getTasks();
		if (!taskProject.contains(task)) {
			taskProject.add(task);
			System.out.println("Task " + task.getTitle() + " added to project " + project.getName());
		} else {
			System.out.println("Task" + task.getTitle() + "is already in project " + project.getName());
		}
	}
}

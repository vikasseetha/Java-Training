package com.rgt.solidprinciples.repositoryimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.rgt.solidprinciples.entity.Project;
import com.rgt.solidprinciples.repository.ProjectRepository;

@Repository
public class InMemoryProjectRepository implements ProjectRepository {
	private List<Project> projects = new ArrayList<>();

	@Override
	public void addProject(Project project) {
		projects.add(project);
	}

	@Override
	public List<Project> getAllProject() {
		return projects;
	}

	@Override
	public Project getProjectByName(String name) {
		for (Project project : projects) {
			if (project.getName().equals(name)) {
				return project;
			}
		}
		return null;
	}

}

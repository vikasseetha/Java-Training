package com.rgt.solidprinciples.repositoryimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.rgt.solidprinciples.entity.Task;
import com.rgt.solidprinciples.repository.TaskRepository;

@Repository
public class InMemoryTaskRepository implements TaskRepository {

	private List<Task> tasks = new ArrayList<>();

	@Override
	public void addTask(Task task) {
		tasks.add(task);
	}

	@Override
	public List<Task> getAllTask() {
		return tasks;
	}

	@Override
	public List<Task> getTasksByPriority(String priority) {
		List<Task> taskWithPriority = new ArrayList<>();
		for (Task task : tasks) {
			taskWithPriority.add(task);
		}
		return taskWithPriority;
	}

	@Override
	public Task getTaskByTitle(String title) {
		for (Task task : tasks) {
			if (task.getTitle().equals(title)) {
				return task;
			}
		}
		return null;
	}
}

package com.rgt.solidprinciples.entity;

import java.time.LocalDate;

public class Task 
{
	private String title;
	private String description;
	private LocalDate dueDate;
	private Priority priority;
	public boolean completed;

	public Task(String title, String description, LocalDate dueDate, Priority priority) {
		super();
		this.title = title;
		this.description = description;
		this.dueDate = dueDate;
		this.priority = priority;
		this.completed = false;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	@Override
	public String toString() {
		return "Task [title=" + title + ", description=" + description + ", dueDate=" + dueDate + ", priority="
				+ priority + ", completed=" + completed + "]";
	}
}

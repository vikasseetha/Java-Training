package com.rgt.solidprinciples;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rgt.solidprinciples.entity.Priority;
import com.rgt.solidprinciples.entity.Project;
import com.rgt.solidprinciples.entity.Task;
import com.rgt.solidprinciples.repository.ProjectRepository;
import com.rgt.solidprinciples.repository.TaskRepository;
import com.rgt.solidprinciples.repositoryimpl.InMemoryProjectRepository;
import com.rgt.solidprinciples.repositoryimpl.InMemoryTaskRepository;
import com.rgt.solidprinciples.service.ProjectService;
import com.rgt.solidprinciples.service.TaskService;
import com.rgt.solidprinciples.serviceimpl.ProjectServiceImpl;
import com.rgt.solidprinciples.serviceimpl.TaskServiceImpl;

@SpringBootApplication
public class SolidPrinciplesVikas24July2023Application {

	public static void main(String[] args) {
		TaskRepository taskRepository = new InMemoryTaskRepository();
		TaskService taskService = new TaskServiceImpl(taskRepository);
		ProjectRepository projectRepository = new InMemoryProjectRepository();
		ProjectService projectService = new ProjectServiceImpl(projectRepository);

		Scanner scanner = new Scanner(System.in);
		System.out.println("User Task Managment Application");
		while (true) {
			System.out.println("\nChoose Option:");
			System.out.println("1. Create Project");
			System.out.println("2. Create Task");
			System.out.println("3. Mark Task as Completed");
			System.out.println("4. Add Task to Project");
			System.out.println("5. View Tasks in a Project");
			System.out.println("6. View Tasks by Priority");
			System.out.println("7. Exit");

			System.out.print("\nchoose an option: ");
			int option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {
			case 1:
				System.out.print("Enter Project Name: ");
				String projectName = scanner.nextLine();
				projectService.createProject(projectName);
				System.out.println("Project created successfully");
				break;
			case 2:
				System.out.print("Enter Task Title: ");
				String title = scanner.nextLine();
				System.out.print("Enter Task Description: ");
				String description = scanner.nextLine();
				LocalDate dueDate = null;
				boolean validDate = false;
				while (!validDate) {
					try {
						System.out.print("Enter Due Date (YYYY-MM-DD): ");
						String dueDateInput = scanner.nextLine();
						dueDate = LocalDate.parse(dueDateInput);
						validDate = true;
					} catch (DateTimeParseException e) {
						System.out.println("Invalid date format! Please enter a date in the format YYYY-MM-DD.");
					}
				}
				System.out.print("Enter Priority: ");
				System.out.print("Enter Priority (LOW, MEDIUM, HIGH): ");
				String priorityInput = scanner.nextLine();
				Priority priority = Priority.valueOf(priorityInput.toUpperCase());
				taskService.createTask(title, description, dueDate, priority);
				System.out.println("Task created successfully!");
				break;
			case 3:
				System.out.print("Enter Task Title: ");
				String taskTitle = scanner.nextLine();
				Task task = taskRepository.getTaskByTitle(taskTitle);
				if (task != null) {
					taskService.completeTask(task);
					System.out.println("Task marked as completed!");
				} else {
					System.out.println("Task not found!");
				}
				break;
			case 4:
				System.out.print("Enter Project Name: ");
				String projects = scanner.nextLine();
				System.out.print("Enter Title Name: ");
				String tasks = scanner.nextLine();
				Project project = projectRepository.getProjectByName(projects);
				Task taskToAdd = taskRepository.getTaskByTitle(tasks);
				if (project != null && tasks != null) {
					projectService.addTaskToProject(project, taskToAdd);
					System.out.println("Task added to project successfully");
				} else {
					System.out.println("Project or Task not found!");
				}
				break;
			case 5:
				System.out.print("Enter Project Name : ");
				String name = scanner.nextLine();
				Project nameOfProject = projectRepository.getProjectByName(name);
				if (nameOfProject != null) {
					List<Task> tasksInProject = projectService.getTasksByProject(nameOfProject);
					System.out.println("Tasks in " + nameOfProject.getName() + " : ");
					for (Task taskInProject : tasksInProject) {
						printTaskDetails(taskInProject);
					}
				} else {
					System.out.println("No project Found");
				}
				break;
			case 6:
				System.out.print("Enter priority (LOW/MEDIUM/HIGH) to view tasks: ");
				String taskpriority = scanner.nextLine();
				List<Task> taskByPriority = taskRepository.getTasksByPriority(taskpriority);
				if (taskByPriority.isEmpty()) {
					System.out.println("No Task Found");
				} else {
					System.out.println("Task With Priority ");
					for (Task taskpriorities : taskByPriority) {
						printTaskDetails(taskpriorities);
					}
				}
				break;
			case 7:
				System.out.println("---------GOODBYE------------------");
				scanner.close();
				return;
			default:
				System.out.println("Invalid option!");
				break;
			}
		}
	}

	private static void printTaskDetails(Task task) {
		System.out.println("Title: " + task.getTitle());
		System.out.println("Description: " + task.getDescription());
		System.out.println("Due Date: " + task.getDueDate());
		System.out.println("Priority: " + task.getPriority());
		System.out.println("Completed: " + (task.isCompleted() ? "Yes" : "No"));
		System.out.println("----------------------");
	}
}

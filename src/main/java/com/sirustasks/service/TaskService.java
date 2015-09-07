package com.sirustasks.service;

import java.util.List;

import com.sirustasks.model.Task;

public interface TaskService {
	Task save(Task task);
	Task findByName(String name);
	List<Task> getAllTasks();
	void deleteTask(String id);
	Task createTask(Task task);
	Task findByTaskId(String id);
	Task updateTask(Task task);
}

package com.sirustasks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sirustasks.model.Task;
import com.sirustasks.repository.TaskRepository;

@Service("taskService")
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskRepository;
	
	@Transactional
	public Task save(Task task) {
		return taskRepository.save(task);
	}

	public Task findByName(String name) {	
		Task task = taskRepository.findByName(name);
		
		if(task != null) {
			return task;
		} 
		
		return new Task();		
	}


	public List<Task> getAllTasks() {
		return taskRepository.findAll();
	}
	
	@Transactional
	public void deleteTask(String id) {
		taskRepository.delete(Integer.parseInt(id));
	}
	

	public Task findByTaskId(String id) {
		return taskRepository.findOne(Integer.parseInt(id));
	}
	
	@Transactional
	public Task createTask(Task task) {
		return taskRepository.save(task);
	}
	
	@Transactional
	public Task updateTask(Task task) {
		return taskRepository.save(task);
	}


}

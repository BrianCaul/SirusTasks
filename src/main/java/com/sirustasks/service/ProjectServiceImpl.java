package com.sirustasks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sirustasks.model.Project;
import com.sirustasks.repository.ProjectRepository;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	
	@Transactional
	public Project save(Project project) {
		return projectRepository.save(project);
	}

	public Project findByName(String name) {	
		Project project = projectRepository.findByName(name);
		
		if(project != null) {
			return project;
		} 
		
		return new Project();		
	}


	public List<Project> getAllProjects() {
		return projectRepository.findAll();
	}
	
	@Transactional
	public void deleteProject(String id) {
		projectRepository.delete(Integer.parseInt(id));
	}
	

	public Project findByProjectId(String id) {
		return projectRepository.findOne(Integer.parseInt(id));
	}
	
	@Transactional
	public Project createProject(Project project) {
		return projectRepository.save(project);
	}
	
	@Transactional
	public Project updateProject(Project project) {
		return projectRepository.save(project);
	}


}

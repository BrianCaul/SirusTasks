package com.sirustasks.service;

import java.util.List;

import com.sirustasks.model.Project;

public interface ProjectService {
	Project save(Project project);
	Project findByName(String name);
	List<Project> getAllProjects();
	void deleteProject(String id);
	Project createProject(Project project);
	Project findByProjectId(String id);
	Project updateProject(Project project);
}

package com.sirustasks.controller.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.sirustasks.model.Project;
import com.sirustasks.service.EventService;
import com.sirustasks.service.ProjectService;
@Controller
@SessionAttributes("project")
public class ProjectRestController {

	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private View jsonView;

	private static final String DATA_FIELD = "data";
	private static final String ERROR_FIELD = "error";

	@RequestMapping(value = "/rest/projects", method = RequestMethod.GET)
	public ModelAndView getAllProjects() {
		List<Project> projects = null;
		Map<String, List<Project>> results = new HashMap<String, List<Project>>();

		try {
			projects = projectService.getAllProjects();
		} catch (Exception e) {
			String sMessage = "Error invoking projects";
			return getErrorJSON(String.format(sMessage, e.toString()));
		}

		results.put("projects", projects);

		return new ModelAndView(jsonView, DATA_FIELD, results);
	}

	@RequestMapping(value = "/rest/projects/{id}", method = RequestMethod.DELETE)
	public ModelAndView deleteProject(@PathVariable String id) {

		try {
			projectService.deleteProject(id);
		} catch (Exception e) {
			String sMessage = "Error invoking delete projects";
			return getErrorJSON(String.format(sMessage, e.toString()));
		}

		return new ModelAndView(jsonView, DATA_FIELD,
				"Sucessfully Deleted ID: " + id);
	}

	@RequestMapping(value = "/rest/projects/{id}", method = RequestMethod.GET)
	public ModelAndView getProject(@PathVariable String id) {
		Project project = new Project();
		try {
			project = projectService.findByProjectId(id);
		} catch (Exception e) {
			String sMessage = "Error invoking find project by id";
			return getErrorJSON(String.format(sMessage, e.toString()));
		}

		return new ModelAndView(jsonView, DATA_FIELD, project);
	}

	
	@RequestMapping(value = "/rest/projects", method = RequestMethod.POST)
	public ModelAndView createProject(
			@RequestParam("title") String title) {

		Project project = new Project();

			try {
				project.setTitle(title);
			
				project = projectService.createProject(project);
			} catch (Exception e) {
				String sMessage = "Error creating Project";
				return getErrorJSON(String.format(sMessage, e.toString()));
			}

		return new ModelAndView(jsonView, DATA_FIELD, project);
	}
	
		
	@RequestMapping(value = "/rest/projects/{id}", method = RequestMethod.POST)
	public ModelAndView updateProject(@PathVariable String id,
			@RequestParam("title") String title) {

		Project project = new Project();
		try {
			
			project = projectService.findByProjectId(id);
			project.setTitle(title);
			project = projectService.updateProject(project);
		} catch (Exception e) {
			String sMessage = "Error updating project";
			return getErrorJSON(String.format(sMessage, e.toString()));
		}

		return new ModelAndView(jsonView, DATA_FIELD, project);
	}
	

	/**
	 * Create an error REST response.
	 * 
	 * @param sMessage
	 * @return
	 */
	private ModelAndView getErrorJSON(String sMessage) {
		return new ModelAndView(jsonView, ERROR_FIELD, sMessage);
	}

}

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

import com.sirustasks.model.Task;
import com.sirustasks.service.EventService;
import com.sirustasks.service.TaskService;
@Controller
@SessionAttributes("task")
public class TaskRestController {

	@Autowired
	private TaskService taskService;
	
	@Autowired
	private EventService eventService;

	@Autowired
	private View jsonView;

	private static final String DATA_FIELD = "data";
	private static final String ERROR_FIELD = "error";

	@RequestMapping(value = "/rest/tasks", method = RequestMethod.GET)
	public ModelAndView getAllTasks() {
		List<Task> tasks = null;
		Map<String, List<Task>> results = new HashMap<String, List<Task>>();

		try {
			tasks = taskService.getAllTasks();
		} catch (Exception e) {
			String sMessage = "Error invoking tasks";
			return getErrorJSON(String.format(sMessage, e.toString()));
		}

		results.put("tasks", tasks);

		return new ModelAndView(jsonView, DATA_FIELD, results);
	}

	@RequestMapping(value = "/rest/tasks/{id}", method = RequestMethod.DELETE)
	public ModelAndView deleteTask(@PathVariable String id) {

		try {
			taskService.deleteTask(id);
		} catch (Exception e) {
			String sMessage = "Error invoking delete tasks";
			return getErrorJSON(String.format(sMessage, e.toString()));
		}

		return new ModelAndView(jsonView, DATA_FIELD,
				"Sucessfully Deleted ID: " + id);
	}

	@RequestMapping(value = "/rest/tasks/{id}", method = RequestMethod.GET)
	public ModelAndView getTask(@PathVariable String id) {
		Task task = new Task();
		try {
			task = taskService.findByTaskId(id);
		} catch (Exception e) {
			String sMessage = "Error invoking find task by id";
			return getErrorJSON(String.format(sMessage, e.toString()));
		}

		return new ModelAndView(jsonView, DATA_FIELD, task);
	}

	@RequestMapping(value = "/rest/tasks", method = RequestMethod.POST)
	public ModelAndView createTask(
			@RequestParam("title") String title,
			@RequestParam("description") String description) {

		Task task = new Task();
			try {
				task.setTitle(title);
				task.setDescription(description);
				task.setCompleted("No");
			
				task = taskService.createTask(task);
			} catch (Exception e) {
				String sMessage = "Error creating Task";
				return getErrorJSON(String.format(sMessage, e.toString()));
			}

		return new ModelAndView(jsonView, DATA_FIELD, task);
	}
	
		
	@RequestMapping(value = "/rest/tasks/{id}", method = RequestMethod.POST)
	public ModelAndView updateTask(@PathVariable String id,
			@RequestParam("title") String title,
			@RequestParam("description") String description) {

		Task task = new Task();
		try {
			
			task = taskService.findByTaskId(id);
			task.setTitle(title);
			task.setDescription(description);
			task = taskService.updateTask(task);
		} catch (Exception e) {
			String sMessage = "Error updating task";
			return getErrorJSON(String.format(sMessage, e.toString()));
		}

		return new ModelAndView(jsonView, DATA_FIELD, task);
	}

	@RequestMapping(value = "/rest/tasks/{id}/complete", method = RequestMethod.POST)
	public ModelAndView updateTask(@PathVariable String id) {

		Task task = new Task();
		try {
			
			task = taskService.findByTaskId(id);
			if(task.getCompleted().contentEquals("No")){
				task.setCompleted("Yes");
			}else{
				task.setCompleted("No");
			}
		
			task = taskService.updateTask(task);
		} catch (Exception e) {
			String sMessage = "Error updating task";
			return getErrorJSON(String.format(sMessage, e.toString()));
		}

		return new ModelAndView(jsonView, DATA_FIELD, task);
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

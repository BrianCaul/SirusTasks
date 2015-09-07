package com.sirustasks.controller.rest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.View;

import com.sirustasks.model.Event;
import com.sirustasks.service.EventService;
import com.sirustasks.service.ProjectService;
import com.sirustasks.service.UserService;
@Controller
@SessionAttributes("event")
public class EventRestController {

	@Autowired
	private EventService eventService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private View jsonView;


	@RequestMapping(value = "/rest/events", method = RequestMethod.GET)
	public @ResponseBody List<Event> getAllEvents() {
		List<Event> events = null;
		try {
			events = eventService.getAllEvents();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return events;
	}

	@RequestMapping(value = "/rest/events/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String deleteEvent(@PathVariable String id) {

		try {
			eventService.deleteEvent(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "Sucessfully Deleted ID: " + id;
	}

	@RequestMapping(value = "/rest/events/{id}", method = RequestMethod.GET)
	public @ResponseBody Event getEvent(@PathVariable String id) {
		Event event = new Event();
		try {
			event = eventService.findByEventId(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return event;
	}
	

	@RequestMapping(value = "/rest/events", method = RequestMethod.POST)
	public  @ResponseBody Event createEvent(
			@RequestParam("title") String title,
			@RequestParam("projectId") String projectId,
			@RequestParam("description") String description,
			@RequestParam("eventtime") String eventtime,
			@RequestParam("userId") String userId,
			@RequestParam("type") String type) {

		Event event = new Event();
		
			try {
				SimpleDateFormat dateformat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
				Date eventTime = dateformat.parse(eventtime);
				
				event.setTitle(title);
				event.setEventTime(eventTime);
				event.setDescription(description);
				event.setEventType(type);
				event.setUser(userService.findByUserId(userId));
				event.setProject(projectService.findByProjectId(projectId));
			
				event = eventService.createEvent(event);
			} catch (Exception e) {
				e.printStackTrace();
			}

		return event;
	}
	
		
	@RequestMapping(value = "/rest/events/{id}", method = RequestMethod.POST)
	public @ResponseBody Event updateEvent(@PathVariable String id,
			@RequestParam("title") String title,
			@RequestParam("projectId") String projectId,
			@RequestParam("description") String description,
			@RequestParam("eventtime") String eventtime,
			@RequestParam("userId") String userId,
			@RequestParam("type") String type) {

		Event event = new Event();
		try {
			event = eventService.findByEventId(id);
			
			SimpleDateFormat dateformat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
			Date eventTime = dateformat.parse(eventtime);
			
			event.setTitle(title);
			event.setEventTime(eventTime);
			event.setDescription(description);
			event.setEventType(type);
			event.setUser(userService.findByUserId(userId));
			event.setProject(projectService.findByProjectId(projectId));
		
			event = eventService.updateEvent(event);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return event;
	}

}

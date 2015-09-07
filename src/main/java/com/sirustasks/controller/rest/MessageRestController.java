package com.sirustasks.controller.rest;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.View;

import com.sirustasks.model.Message;
import com.sirustasks.service.MessageService;
import com.sirustasks.service.TaskService;
import com.sirustasks.service.UserService;
@Controller
@SessionAttributes("message")
public class MessageRestController {

	@Autowired
	private MessageService messageService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TaskService taskService;

	@Autowired
	private View jsonView;


	@RequestMapping(value = "/rest/messages", method = RequestMethod.GET)
	public @ResponseBody List<Message> getAllMessages() throws JsonGenerationException, JsonMappingException, IOException {
		List<Message> messages = null;

		try {
			messages = messageService.getAllMessages();
		} catch (Exception e) {
			e.printStackTrace();  
		}
		return messages;
	}

	@RequestMapping(value = "/rest/messages/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String deleteMessage(@PathVariable String id) {

		try {
			messageService.deleteMessage(id);
		} catch (Exception e) {
			e.printStackTrace();  
		}

		return "Sucessfully Deleted ID: " + id;
	}

	@RequestMapping(value = "/rest/messages/{id}", method = RequestMethod.GET)
	public @ResponseBody Message getMessage(@PathVariable String id) {
		Message message = new Message();
		try {
			message = messageService.findByMessageId(id);
		} catch (Exception e) {
			e.printStackTrace();  
		}

		return message;
	}

	@RequestMapping(value = "/rest/messages", method = RequestMethod.POST)
	public @ResponseBody Message createMessage(
			@RequestParam("comment") String comment,
			@RequestParam("taskId") String taskId,
			@RequestParam("userId") String userId) {

		Message message = new Message();


			try {
				message.setComment(comment);
				message.setUser(userService.findByUserId(userId));
				message.setTask(taskService.findByTaskId(taskId));
				
				message = messageService.createMessage(message);
			} catch (Exception e) {
				e.printStackTrace();  
			}

	

		return message;
	}
	
		
	@RequestMapping(value = "/rest/messages/{id}", method = RequestMethod.POST)
	public @ResponseBody Message updateMessage(@PathVariable String id,
			@RequestParam("comment") String comment,
			@RequestParam("taskId") String taskId,
			@RequestParam("userId") String userId) {

		Message message = new Message();
		try {
			
			message = messageService.findByMessageId(id);
			message.setComment(comment);
			message.setUser(userService.findByUserId(userId));
			message.setTask(taskService.findByTaskId(taskId));
			message = messageService.updateMessage(message);
		} catch (Exception e) {
			e.printStackTrace();  
		}

		return message;
	}



}

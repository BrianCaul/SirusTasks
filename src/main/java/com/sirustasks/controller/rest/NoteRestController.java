package com.sirustasks.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.sirustasks.model.Note;
import com.sirustasks.service.NoteService;
import com.sirustasks.service.UserService;
@Controller
@SessionAttributes("note")
public class NoteRestController {

	@Autowired
	private NoteService noteService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private View jsonView;

	private static final String DATA_FIELD = "data";
	private static final String ERROR_FIELD = "error";

	@RequestMapping(value = "/rest/notes", method = RequestMethod.GET)
	public @ResponseBody List<Note> getAllNotes() {
		List<Note> notes = null;

		try {
			notes = noteService.getAllNotes();
		} catch (Exception e) {
			String sMessage = "Error invoking notes";
			return notes;
		}


		return notes;
	}

	@RequestMapping(value = "/rest/notes/{id}", method = RequestMethod.DELETE)
	public ModelAndView deleteNote(@PathVariable String id) {

		try {
			noteService.deleteNote(id);
		} catch (Exception e) {
			String sMessage = "Error invoking delete notes";
			return getErrorJSON(String.format(sMessage, e.toString()));
		}

		return new ModelAndView(jsonView, DATA_FIELD,
				"Sucessfully Deleted ID: " + id);
	}

	@RequestMapping(value = "/rest/notes/{id}", method = RequestMethod.GET)
	public ModelAndView getNote(@PathVariable String id) {
		Note note = new Note();
		try {
			note = noteService.findByNoteId(id);
		} catch (Exception e) {
			String sMessage = "Error invoking find note by id";
			return getErrorJSON(String.format(sMessage, e.toString()));
		}

		return new ModelAndView(jsonView, DATA_FIELD, note);
	}

	@RequestMapping(value = "/rest/notes", method = RequestMethod.POST)
	public @ResponseBody Note createNote(@RequestParam("title") String title,
			@RequestParam("content") String content,
			@RequestParam("userId") String userId,
			@RequestParam(value="id", required = false) int id,
			@RequestParam(value="created", required = false) int created) {

		Note note = new Note();
			try {
				note.setTitle(title);
				note.setContent(content);
				note.setUser(userService.findByUserId(userId));
			
				note = noteService.createNote(note);
			} catch (Exception e) {
				String sMessage = "Error creating Note";
				return note;
			}

		return note;
	}
	
		
	@RequestMapping(value = "/rest/notes/{id}", method = RequestMethod.POST)
	public ModelAndView updateNote(@PathVariable String id,
			@RequestParam("title") String title,
			@RequestParam("content") String content,
			@RequestParam("userId") String userId) {

		Note note = new Note();
		try {
			
			note.setTitle(title);
			note.setContent(content);
			note.setUser(userService.findByUserId(userId));
	
			note = noteService.updateNote(note);
		} catch (Exception e) {
			String sMessage = "Error updating note";
			return getErrorJSON(String.format(sMessage, e.toString()));
		}

		return new ModelAndView(jsonView, DATA_FIELD, note);
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

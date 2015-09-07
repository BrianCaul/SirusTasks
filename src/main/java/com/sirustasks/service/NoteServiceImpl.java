package com.sirustasks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sirustasks.model.Note;
import com.sirustasks.repository.NoteRepository;

@Service("noteService")
public class NoteServiceImpl implements NoteService {

	@Autowired
	private NoteRepository noteRepository;
	
	@Autowired
	private EventService eventService;
	
	@Transactional
	public Note save(Note note) {
		return noteRepository.save(note);
	}

	public Note findByName(String name) {	
		Note note = noteRepository.findByName(name);
		
		if(note != null) {
			return note;
		} 
		
		return new Note();		
	}


	public List<Note> getAllNotes() {
		return noteRepository.findAll();
	}
	
	@Transactional
	public void deleteNote(String id) {
		noteRepository.delete(Integer.parseInt(id));
	}
	

	public Note findByNoteId(String id) {
		return noteRepository.findOne(Integer.parseInt(id));
	}
	
	@Transactional
	public Note createNote(Note note) {
		return noteRepository.save(note);
	}
	
	@Transactional
	public Note updateNote(Note note) {
		return noteRepository.save(note);
	}


}

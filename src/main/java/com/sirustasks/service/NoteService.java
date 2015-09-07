package com.sirustasks.service;

import java.util.List;

import com.sirustasks.model.Note;

public interface NoteService {
	Note save(Note note);
	Note findByName(String name);
	List<Note> getAllNotes();
	void deleteNote(String id);
	Note createNote(Note note);
	Note findByNoteId(String id);
	Note updateNote(Note note);
}

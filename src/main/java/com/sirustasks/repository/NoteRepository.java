package com.sirustasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sirustasks.model.Note;

@Repository("noteRepository")
public interface NoteRepository extends JpaRepository<Note, Integer> {
	
	@Query("select n from Note n where n.title = :title")
	Note findByName(@Param("title") String title);
	
}

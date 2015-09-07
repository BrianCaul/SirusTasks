package com.sirustasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sirustasks.model.Note;
import com.sirustasks.model.Task;

@Repository("taskRepository")
public interface TaskRepository extends JpaRepository<Task, Integer> {
	
	@Query("select t from Task t where t.title = :title")
	Task findByName(@Param("title") String title);
}

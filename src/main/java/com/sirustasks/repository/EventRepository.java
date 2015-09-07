package com.sirustasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sirustasks.model.Event;

@Repository("eventRepository")
public interface EventRepository extends JpaRepository<Event, Integer> {
	
	@Query("select e from Event e where e.title = :title")
	Event findByName(@Param("title") String title);
	
}

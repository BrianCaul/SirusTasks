package com.sirustasks.service;

import java.util.List;
import java.util.Set;

import com.sirustasks.model.Event;

public interface EventService {
	Event save(Event event);
	Event findByName(String name);
	List<Event> getAllEvents();
	void deleteEvent(String id);
	Event createEvent(Event event);
	Event findByEventId(String id);
	Event updateEvent(Event event);
}

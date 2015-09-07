package com.sirustasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sirustasks.model.Message;

@Repository("messageRepository")
public interface MessageRepository extends JpaRepository<Message, Integer> {
	
	
}

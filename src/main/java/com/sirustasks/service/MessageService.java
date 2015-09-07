package com.sirustasks.service;

import java.util.List;

import com.sirustasks.model.Message;

public interface MessageService {
	Message save(Message message);
	List<Message> getAllMessages();
	void deleteMessage(String id);
	Message createMessage(Message message);
	Message findByMessageId(String id);
	Message updateMessage(Message message);
}

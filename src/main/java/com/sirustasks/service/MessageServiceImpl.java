package com.sirustasks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sirustasks.model.Message;
import com.sirustasks.repository.MessageRepository;

@Service("messageService")
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageRepository messageRepository;
	
	@Transactional
	public Message save(Message message) {
		return messageRepository.save(message);
	}


	public List<Message> getAllMessages() {
		return messageRepository.findAll();
	}
	
	@Transactional
	public void deleteMessage(String id) {
		messageRepository.delete(Integer.parseInt(id));
	}
	

	public Message findByMessageId(String id) {
		return messageRepository.findOne(Integer.parseInt(id));
	}
	
	@Transactional
	public Message createMessage(Message message) {
		return messageRepository.save(message);
	}
	
	@Transactional
	public Message updateMessage(Message message) {
		return messageRepository.save(message);
	}


}

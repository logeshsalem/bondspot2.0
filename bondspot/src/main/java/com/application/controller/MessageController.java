package com.application.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.application.entity.Message;
import com.application.service.MessageService;

@RestController
@RequestMapping("/api")
public class MessageController {
	
	private MessageService messageService;
	
	public MessageController(MessageService theMessageService) {
		messageService = theMessageService;
	}

	@PostMapping("/message")
	public Message sendMessage(@RequestBody Message message) {
	        Message sentMessage = messageService.saveMessage(
	           message.getSender().getId(), 
	           message.getReceiver().getId(), 
	           message.getContent()
	        );	         
	      return sentMessage;	        
	}
	
	 @GetMapping("/conversation")
	 public List<Message> getConversation(
	            @RequestParam int userId1, 
	            @RequestParam int userId2) {
	      List<Message> messages = messageService.getConversation(userId1, userId2);
	      return messages;
	 }
	
	
}

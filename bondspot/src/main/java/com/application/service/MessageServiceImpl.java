package com.application.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import com.application.entity.Message;
import com.application.entity.User;
import com.application.repository.MessageRepository;
import com.application.repository.UserRepository;

@Service
public class MessageServiceImpl implements MessageService{
	
	private MessageRepository messageRepository;
	private UserRepository userRepository;
	
	public MessageServiceImpl(MessageRepository theMessageRepository, UserRepository theRepository) {
		messageRepository = theMessageRepository;
		userRepository = theRepository;
	}

	@Override
	public Message saveMessage(int senderId, int receiverId, String content) {
		 User sender = userRepository.findById(senderId)
		            .orElseThrow(() -> new RuntimeException("Sender not found"));
		 User receiver = userRepository.findById(receiverId)
		            .orElseThrow(() -> new RuntimeException("Receiver not found"));
		
		 Message message = new Message();
		 message.setReceiver(receiver);
		 message.setSender(sender);
		 message.setSentAt(LocalDateTime.now());
		 message.setContent(content);
		 
		 return messageRepository.save(message);	 
		 
	}

	@Override
	public List<Message> getConversation(int userId1, int userId2) {		
		  User user1 = userRepository.findById(userId1)
		      .orElseThrow(() -> new RuntimeException("User1 not found"));
		  User user2 = userRepository.findById(userId2)
		      .orElseThrow(() -> new RuntimeException("User2 not found"));
		  
		  List<Message> messages = messageRepository.findBySenderAndReceiver(user1, user2);
	        messages.addAll(messageRepository.findBySenderAndReceiver(user2, user1));
	        messages.sort((m1, m2) -> m1.getSentAt().compareTo(m2.getSentAt()));
	        
	      return messages;
	}
	
	

}

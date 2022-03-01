package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Message;
import tn.esprit.spring.repositories.MessageRepository;

@Service
public class MessageService implements IMessageService {

	@Autowired
	MessageRepository messageRepository;

	@Override
	public void addMessage(Message m) {
		messageRepository.save(m);
	}

	@Override
	public List<Message> sortByDateA() {
		return messageRepository.findAll(Sort.by(Direction.ASC,"date"));
	}

	@Override
	public List<Message> sortByDateD() {
		return messageRepository.findAll(Sort.by(Direction.DESC,"date"));
	}
	
	@Override
	public List<Message> listMessages(){
		return messageRepository.findAll();
	}
	
	@Override
	public List<Message> searchMessages(String s) {
		List<Message> msgs=new ArrayList<Message>();
		List<Message> allmsgs=(List<Message>)messageRepository.findAll();
		for(Message m : allmsgs){
			if (m.getBody().contains(s)) {
				msgs.add(m);
			}
		}
		return msgs;
	}
}

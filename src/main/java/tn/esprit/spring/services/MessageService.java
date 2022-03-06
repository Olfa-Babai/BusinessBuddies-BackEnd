package tn.esprit.spring.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Chat;
import tn.esprit.spring.entities.Message;
import tn.esprit.spring.repositories.ChatRepository;
import tn.esprit.spring.repositories.MessageRepository;
import tn.esprit.spring.repositories.UserRepository;

@Service
public class MessageService implements IMessageService {

	@Autowired
	MessageRepository messageRepository;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ChatRepository chatRepository;
	
	@Autowired
	IChatService chatService;
	
	@Override
	public void addMessage(Message m, long ids, long idr) {
		Message message=new Message();
		boolean test=false;
		m.setSender(userRepository.getById(ids));
		m.setReceiver(userRepository.getById(idr));
		for(Message msg : messageRepository.findAll()){
			if (msg.getReceiver().equals(m.getReceiver()) || msg.getSender().equals(m.getReceiver())){
				message=msg;
				test=true;
			}
		}
		if(test==true){
			int n=message.getChat().getNbmessages()+1;
			message.getChat().setNbmessages(n);
			m.setChat(message.getChat());
		}
		else
		{
			Chat c=new Chat();
			c.setNbmessages(1);
			m.setChat(chatService.addChat(c));
		} 
	    m.setDate(LocalDateTime.now());
		messageRepository.save(m);
	}


	@Override
	public List<Message> sortByDateD() {
		return messageRepository.findAll(Sort.by(Direction.DESC,"date"));
	}
	
	@Override
	public List<Message> listMessages(long ids, long idr){
		List<Message> ms=new ArrayList<Message>();
		for(Message m : messageRepository.findAll()){
			if((m.getSender().getUser_Id()==ids || m.getSender().getUser_Id()==idr) && (m.getReceiver().getUser_Id()==ids || m.getReceiver().getUser_Id()==idr)){
				ms.add(m);
			} }
		return ms;
	}
	
	@Override
	public List<Message> searchMessages(String s, long idu) {
		List<Message> msgs=new ArrayList<Message>();
		List<Message> allmsgs=(List<Message>)messageRepository.findAll();
		for(Message m : allmsgs){
			if (m.getBody().contains(s) && (m.getSender().getUser_Id()==idu || m.getReceiver().getUser_Id()==idu)) {
				msgs.add(m);
			}
		}
		return msgs;
	}
}

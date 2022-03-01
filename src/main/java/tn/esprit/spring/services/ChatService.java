package tn.esprit.spring.services;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.*;
import tn.esprit.spring.repositories.ChatRepository;

@Service
public class ChatService implements IChatService{

	@Autowired
	ChatRepository chatRepository;

	@Override
	public void addChat(Chat c) {
		chatRepository.save(c);
	}

	@Override
	public List<Chat> listChats() {
		return (List<Chat>) chatRepository.findAll();
	}

	// recherche a travers un message échangé
	@Override
	public List<Chat> research(String research) {
		List<Chat> chats=new ArrayList<Chat>();
		List<Chat> allchats=(List<Chat>)chatRepository.findAll();
		for(Chat c : allchats){
			for(Message m : c.getMessages()){
				if (m.getBody().contains(research)){
					chats.add(c);
				}
			}
		}
		return chats;
	}

	//sort chat by number of messages
	@Override
	public List<Chat> sortChat(String sortType) {
		
		return null;
	}

	@Override
	public void deleteChat(int idc) {
		if (containsChat(idc)){
			chatRepository.delete(chatRepository.findById(idc).orElse(null));
		}
	}
	
	@Override
	public Chat getChatById(int idc) {
		return chatRepository.findById(idc).orElse(null);
	}

	public boolean containsChat(int idc){
		return chatRepository.findById(idc).orElse(null)!=null;
	}
	
}

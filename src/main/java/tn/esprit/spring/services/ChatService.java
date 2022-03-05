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
	public Chat addChat(Chat c) {
		return chatRepository.save(c);
	}

	@Override
	public List<Chat> listChats(long idu) {
		List<Chat> listChats=new ArrayList<Chat>();
		boolean test=false;
		for(Chat c : chatRepository.findAll()){
			for(Message m : c.getMessages()){
				if(m.getReceiver().getUser_Id()==idu || m.getSender().getUser_Id()==idu){
					test=true;
				}
			if (test==true){
				listChats.add(c);
			}
			}
		}
		return listChats;
	}

	// recherche a travers un message échangé
	@Override
	public List<Chat> research(String research, long idu) {
		List<Chat> chats=new ArrayList<Chat>();
		List<Chat> allchats=(List<Chat>)chatRepository.findAll();
		for(Chat c : allchats){
			for(Message m : c.getMessages()){
				if (m.getBody().contains(research) && (m.getReceiver().getUser_Id()==idu || m.getSender().getUser_Id()==idu) ){
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

	@Override
	public void addChat(Chat c, long ids, long idr) {
		// TODO Auto-generated method stub
		
	}
	
}

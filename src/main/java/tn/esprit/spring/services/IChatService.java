package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Chat;

public interface IChatService {

	void addChat(Chat c);
	List<Chat> listChats();
	List<Chat> research(String research);
	List<Chat> sortChat(String sortType);
	Chat getChatById(int idc);
	void deleteChat(int idc);
	
}

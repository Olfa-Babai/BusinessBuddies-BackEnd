package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Chat;

public interface IChatService {

	void addChat(Chat c,long ids,long idr);
	List<Chat> listChats(long idu);
	List<Chat> research(String research, long idu);
	List<Chat> sortChat(String sortType);
	Chat getChatById(int idc);
	void deleteChat(int idc);
	Chat addChat(Chat c);
}

package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Message;

public interface IMessageService {

	void addMessage(Message m);
	// send message
	List<Message> sortByDateA();
	List<Message> sortByDateD();
	List<Message> searchMessages(String s);
	List<Message> listMessages();
}

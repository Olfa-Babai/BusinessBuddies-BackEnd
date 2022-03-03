package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Message;

public interface IMessageService {

	void addMessage(Message m, long ids, long idr);
	List<Message> sortByDateD();
	List<Message> searchMessages(String s,long idu);
	List<Message> listMessages(long ids, long idr);
}

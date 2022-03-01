package tn.esprit.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Message;
import tn.esprit.spring.services.*;

@RestController
@RequestMapping("/message")
public class MessageController {
	
	@Autowired
	IMessageService messageService;
	
	@PostMapping("/add-message")
	@ResponseBody
	void addMessage(@RequestBody Message m){
		messageService.addMessage(m);
	}
	
	@GetMapping("/sorting-messages/{s}")
	@ResponseBody
	List<Message> sortingMessages(@PathVariable("s") String s){
		if(s.toLowerCase().equals("asc")){
			return messageService.sortByDateA();
		}
		else if (s.toLowerCase().equals("desc")){
			return messageService.sortByDateD();
		}
		else if (s.toLowerCase().equals("none")){
			return messageService.listMessages();
		}
		else return null;
	}
	
	@GetMapping("/search-messages/{m}")
	@ResponseBody
	List<Message> searchMessages(@PathVariable("m") String m){
		return messageService.searchMessages(m);
	}
}

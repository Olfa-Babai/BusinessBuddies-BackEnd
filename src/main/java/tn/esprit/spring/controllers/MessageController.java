package tn.esprit.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Message;
import tn.esprit.spring.services.*;

@RestController
@RequestMapping("/message")
public class MessageController {
	
	@Autowired
	IMessageService messageService;
	
	@PostMapping("/add-message/{ids}/{idr}")
	@ResponseBody
	void addMessage(@RequestBody Message m,@PathVariable("ids") long ids,@PathVariable("idr") long idr){
		messageService.addMessage(m,ids,idr);
	}
	
	@GetMapping("/list-messages/{ids}/{idr}")
	@ResponseBody
	List<Message> listConversation(@PathVariable("ids") long ids,@PathVariable("idr") long idr){
		return messageService.listMessages(ids,idr);
	}
	
	@GetMapping("/search-messages/{idu}")
	@ResponseBody
	List<Message> searchMessages(@RequestParam String m, @PathVariable("idu") long idu){
		return messageService.searchMessages(m,idu);
	}
}

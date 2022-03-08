package tn.esprit.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.MailMessage;
import tn.esprit.spring.entities.Message;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repositories.UserRepository;
import tn.esprit.spring.services.*;

@RestController
@RequestMapping("/message")
public class MessageController {
	
	@Autowired
	IMessageService messageService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	EmailServiceCommunication emailService;
	
	@Autowired
	IDictionnaryService dictionnaryService;
	
	@PostMapping("/add-message/{ids}/{idr}")
	@ResponseBody
	void addMessage(@RequestBody Message m,@PathVariable("ids") long ids,@PathVariable("idr") long idr){
		m.setBody(dictionnaryService.wordsChecked(m.getBody()));
		messageService.addMessage(m,ids,idr);
		 User ur=userRepository.findById(idr).get();
		 User us=userRepository.findById(ids).get();
		if(ur.getMailMessage().equals(MailMessage.activated)){
			emailService.SendMail(ur.getEmail(),
					"New message!",
					"We would like to inform you that you recieved a message from "+
					us.getUserFirstName()+" "+us.getUserName());
		} 
	}
	
	@PutMapping("/activate/{idu}")
	@ResponseBody
	void activateMessage(@PathVariable("idu") long idu){
		User u=userRepository.findById(idu).get();
		u.setMailMessage(MailMessage.activated);
		userRepository.save(u);
	}
	
	@PutMapping("/deactivate/{idu}")
	@ResponseBody
	void deactivateMessage(@PathVariable("idu")  long idu){
		User u=userRepository.findById(idu).get();
		u.setMailMessage(MailMessage.deactivated);
		userRepository.save(u);
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

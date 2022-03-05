package tn.esprit.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.*;
import tn.esprit.spring.services.IChatService;

@RestController
@RequestMapping("/chat")
public class ChatController {

	@Autowired
	IChatService chatService;
	
	@PostMapping("/add-chat")
	@ResponseBody
	public void addChat(@RequestBody Chat c){
		chatService.addChat(c);
	}
	
	@GetMapping("/list-chats/{idu}")
	@ResponseBody
	public List<Chat> listChats(@PathVariable("idu") int idu){
		return chatService.listChats(idu);
	}
	
	@GetMapping("/research-chats/{idu}")
	@ResponseBody
	public List<Chat> researchChat(@PathVariable("idu") long idu,@RequestParam String research){
		return chatService.research(research, idu);
	}
	
	@DeleteMapping("/delete-chat/{id}")
	@ResponseBody
	public void deleteChat(@PathVariable("id") int id){
		chatService.deleteChat(id);
	}
}

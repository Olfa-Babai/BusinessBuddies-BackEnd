package tn.esprit.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Comment;
import tn.esprit.spring.entities.Post;
import tn.esprit.spring.services.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	ICommentService commentService;
	@Autowired 
	IDictionnaryService dictionnaryService;
	
	@PostMapping("/add-comment/{idp}/{idu}")
	@ResponseBody
	public void addComment(@PathVariable("idp")int idp, @PathVariable("idu")long idu , @RequestBody Comment c){
		c.setBody(dictionnaryService.wordsChecked(c.getBody()));
		commentService.addComment(c, idp, idu);
	}
	
	@PutMapping("/update-comment/{idc}")
	@ResponseBody
	public void updateComment(@PathVariable("idc")int idc, @RequestBody Comment c){
		c.setBody(dictionnaryService.wordsChecked(c.getBody()));
		commentService.updateComment(c, idc);
	}
	
	@DeleteMapping("/delete-comment/{idc}")
	@ResponseBody
	public void deleteComment(@PathVariable("idc")int idc){
		commentService.deleteComment(idc);
	}
	
	@GetMapping("/show-comments/{idp}")
	@ResponseBody
	public List<Comment> viewCommentsByPost(@PathVariable("idp")int idp){
		return commentService.viewCommentsByPost(idp);
	}
	
	@GetMapping("/sort-comments/{idp}")
	@ResponseBody
	public List<Comment> recentCommentsByPost(@PathVariable("idp")int idp){
		return commentService.recentCommentsByPost(idp);
	}
	
	@GetMapping("/most-comments")
	@ResponseBody
	public Post mostCommentedPost(@RequestParam String theme){
		return commentService.mostCommentedPost(theme);
	}
}

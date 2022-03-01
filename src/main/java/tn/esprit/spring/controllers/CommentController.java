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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Comment;
import tn.esprit.spring.services.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	ICommentService commentService;
	
	@PostMapping("/add-comment/{idp}")
	@ResponseBody
	public void addComment(@PathVariable("idp")int idp, @RequestBody Comment c){
		commentService.addComment(c, idp);
	}
	
	@PutMapping("/update-comment/{idc}")
	@ResponseBody
	public void updateComment(@PathVariable("idc")int idc, Comment c){
		commentService.updateComment(c, idc);
	}
	
	@DeleteMapping("/delete-comment/{idc}")
	@ResponseBody
	public void deleteComment(@PathVariable("idc")int idc){
		commentService.deleteComment(idc);
	}
	
	@GetMapping("/show-post/{idp}")
	@ResponseBody
	public List<Comment> viewCommentsByPost(@PathVariable("idp")int idp){
		return commentService.viewCommentsByPost(idp);
	}
	
}

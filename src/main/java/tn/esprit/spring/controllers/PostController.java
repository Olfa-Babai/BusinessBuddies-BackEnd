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

import tn.esprit.spring.entities.Post;
import tn.esprit.spring.services.*;

@RestController
@RequestMapping("/post")
public class PostController {

	@Autowired
	IPostService postService;
	
	@PostMapping("/add-post/{idu}")
	@ResponseBody
	public void addpost(@PathVariable("idu") long idu,@RequestBody Post p){
		postService.addPost(idu,p);
	}
	
	@PutMapping("/update-post/{idp}")
	@ResponseBody
	public void updatePost(@RequestBody Post p, @PathVariable("idp") int idp){
		postService.updatePost(p, idp);
	}
	
	@DeleteMapping("/delete-post")
	@ResponseBody
	public void deletePost(@PathVariable("idp") int idp){
		postService.deletePost(idp);
	}
	
	@GetMapping("/show-post/{theme}")
	@ResponseBody
	public List<Post> showPostsByTheme(@PathVariable("theme") String theme){
		if(theme.toLowerCase().equals("all")){
			return postService.showAllPosts();
		}
		else 
		return postService.showPostsByTheme(theme);
	}
	
	@GetMapping("/show-post/{theme}/{research}")
	@ResponseBody
	public List<Post> searchPosts(@PathVariable("theme") String t,@PathVariable("research") String r){
		return postService.searchPosts(r, t);
	}
}


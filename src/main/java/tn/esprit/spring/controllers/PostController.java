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

import tn.esprit.spring.entities.Post;
import tn.esprit.spring.services.*;

@RestController
@RequestMapping("/post")
public class PostController {

	@Autowired
	IPostService postService;
	
	@Autowired
	IDictionnaryService dictionnaryService;
	
	@PostMapping("/add-post/{idu}")
	@ResponseBody
	public void addpost(@PathVariable("idu") long idu,@RequestBody Post p){
		p.setTitle(dictionnaryService.wordsChecked(p.getTitle()));
		p.setBody(dictionnaryService.wordsChecked(p.getBody()));
		postService.addPost(idu,p);
	}
	
	@PutMapping("/update-post/{idp}")
	@ResponseBody
	public void updatePost(@RequestBody Post p, @PathVariable("idp") int idp){
		p.setTitle(dictionnaryService.wordsChecked(p.getTitle()));
		p.setBody(dictionnaryService.wordsChecked(p.getBody()));
		postService.updatePost(p, idp);
	}
	
	@DeleteMapping("/delete-post/{idp}")
	@ResponseBody
	public void deletePost(@PathVariable("idp") int idp){
		postService.deletePost(idp);
	}
	
	@GetMapping("/show-post/")
	@ResponseBody
	public List<Post> showPostsByTheme(@RequestParam String theme){
		if(theme.toLowerCase().equals("all")){
			return postService.showAllPosts();
		}
		else 
		return postService.showPostsByTheme(theme);
	}
	
	//meg kel elnézni
	@GetMapping("/search-post/")
	@ResponseBody
	public List<Post> searchPosts(@RequestParam String t,@RequestParam String r){
		return postService.searchPosts(r, t);
	}
	
	@GetMapping("/recommend-post/")
	@ResponseBody
	public List<Post> recommendedPosts(){
		return null;
	}
}


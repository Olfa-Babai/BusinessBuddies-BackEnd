package tn.esprit.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Like;
import tn.esprit.spring.services.ILikeService;

@RestController
@RequestMapping("/like")
public class LikeController {

	@Autowired
	ILikeService likeService;
		
	@PostMapping("/add-like/{idp}")
	@ResponseBody
	public void addLike(@PathVariable("idp")int idp, @RequestBody Like like){
		likeService.addLike(idp, like);
	}
	
	@DeleteMapping("/delete-post/{idl}")
	@ResponseBody
	public void removeLike(@PathVariable("idl")int idl) {
		likeService.removeLike(idl);
	}
	
	@GetMapping("/show-post-likes/{idp}")
	@ResponseBody
	public List<Like> showPostsLikes(@PathVariable("idp")int idp) {
		return likeService.showPostsLikes(idp);
	}
	@GetMapping("/show-post-dislikes/{idp}")
	@ResponseBody
	public List<Like> showPostsDislikes(@PathVariable("idp")int idp) {
		return likeService.showPostsDislikes(idp);
	}
}

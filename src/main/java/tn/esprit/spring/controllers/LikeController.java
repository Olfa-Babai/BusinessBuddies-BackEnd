package tn.esprit.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Like;
import tn.esprit.spring.entities.Post;
import tn.esprit.spring.entities.Theme;
import tn.esprit.spring.entities.TypeLike;
import tn.esprit.spring.services.ILikeService;

@RestController
@RequestMapping("/like")
@CrossOrigin(origins = "*")
public class LikeController {

	@Autowired
	ILikeService likeService;
		
	@PostMapping("/add-like/{idp}/{idu}")
	@ResponseBody
	public void addLike(@PathVariable("idp")int idp,@PathVariable("idu")long idu, @RequestBody Like like){
		like.setType(TypeLike.like);
		likeService.addLike(idp,idu,like);
	}
	
	@DeleteMapping("/delete-like/{idl}")
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
	
	@GetMapping("/most-liked-theme/{idu}")
	@ResponseBody
	public Theme mostLikedPosts(@PathVariable("idu")long idu){
			return likeService.mostLikedPostsTheme(idu);	
	}
	
	@GetMapping("/most-liked-post")
	@ResponseBody
	public Post mostLikedPost(@RequestParam String theme){
		return likeService.mostLikedPost(theme);
	}
	
}

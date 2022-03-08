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

import tn.esprit.spring.entities.Followage;
import tn.esprit.spring.entities.Theme;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.services.IFollowageService;

@RestController
@RequestMapping("/follow")
public class FollowageController {

	@Autowired
	IFollowageService followageService;
	
	//follow
	
	@PostMapping("/add-followage/{idu}")
	@ResponseBody
	public void addFollow(@PathVariable("idu") long idu,@RequestBody Followage f ){
		followageService.addFollow(idu, f);
	}
	
	//unfollow
	
	@DeleteMapping("/delete-followage/{idf}")
	@ResponseBody
	public void unFollow(@PathVariable("idf") int idf ){
		followageService.unfollow(idf);
	}
	
	// a user's followings 
	
	@GetMapping("/find-followings/{idu}")
	@ResponseBody
	public List<Followage> followingsOfUser(@PathVariable("idu") long idu){
		return followageService.followingsOfU(idu);
	}
	
	// the nb of followings
	
	@GetMapping("/nb-followings/{idu}")
	@ResponseBody
	public int nbfollowingsOfUser(@PathVariable("idu") long idu){
		return followageService.followingsOfU(idu).size();
	}
	
	//nb of a themes followers
	@GetMapping("/nb-followers")
	@ResponseBody
	public int nbFollowersTheme(@RequestParam String theme){
		return followageService.followersTheme(theme).size();
	}
	
	// list of a themes followers
	
	@GetMapping("/theme-followers")
	@ResponseBody
	public List<User> followersTheme(@RequestParam String theme){
		return followageService.followersTheme(theme);
	}
	
	// best rated theme (of a user)
	
	@GetMapping("/best-rated-of-user/{idu}")
	@ResponseBody
	public Followage bestRatedUser(@PathVariable("idu") long idu){
		return followageService.bestRatedThemeUser(idu);
	}
	
	// best rated theme in general

	@GetMapping("/best-rated-theme")
	@ResponseBody
	public String bestRated(){
		return followageService.bestRatedTheme();
	}
	
	// search themes 
	@GetMapping("/search-theme")
	@ResponseBody
	public List<String> research(@RequestParam String theme){
		return followageService.searchTheme(theme);
	}
	
	//sorting themes
	
	@GetMapping("/sort-theme")
	@ResponseBody
	public List<Theme> sortingThemes(@RequestParam String sort){
		return followageService.sortThemes(sort);
	}
}

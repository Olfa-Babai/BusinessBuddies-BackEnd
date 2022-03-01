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
import tn.esprit.spring.services.IFollowageService;

@RestController
@RequestMapping("/follow")
public class FollowageController {

	@Autowired
	IFollowageService followageService;
	
	@PostMapping("/add-followage/{idu}")
	@ResponseBody
	public void addFollow(@PathVariable("idu") long idu,@RequestBody Followage f ){
		followageService.addFollow(idu, f);
	}
	
	@DeleteMapping("/delete-followage/{idf}")
	@ResponseBody
	public void unFollow(@PathVariable("idf") int idf ){
		followageService.unfollow(idf);
	}
	
	@GetMapping("/find-followage/{idu}")
	@ResponseBody
	public Followage findByThemeAndUser(@PathVariable("idu") long idu,@RequestParam String theme) {
		return followageService.findByThemeAndUser(idu, theme);
	}
	
	@GetMapping("/find-followings/{idu}")
	@ResponseBody
	public List<Followage> followingsOfUser(@PathVariable("idu") long idu){
		return followageService.followersU(idu);
	}
	
	@GetMapping("/nb-followings/{idu}")
	@ResponseBody
	public int nbfollowingsOfUser(@PathVariable("idu") long idu){
		return followageService.followersU(idu).size();
	}
	
}

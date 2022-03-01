package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public void addFollow(@PathVariable("idu") int idu,@RequestBody Followage f ){
		followageService.addFollow(idu, f);
	}
	
}

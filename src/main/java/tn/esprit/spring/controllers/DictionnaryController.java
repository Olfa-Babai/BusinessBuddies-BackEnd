package tn.esprit.spring.controllers;

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

import tn.esprit.spring.entities.Dictionnary;
import tn.esprit.spring.services.IDictionnaryService;

@RestController
@RequestMapping("/word")
public class DictionnaryController {

	@Autowired
	IDictionnaryService dictionnaryService;
	
	@GetMapping("/check-word/")
	@ResponseBody
	public String wordsChecked(@RequestParam String ch){
		return dictionnaryService.wordsChecked(ch);
	}
	
	@PostMapping("/add-word")
	@ResponseBody
	public void addWord(@RequestBody Dictionnary d){
		dictionnaryService.addDictionnary(d);
	}
	
	@PutMapping("/update-word/{idd}")
	@ResponseBody
	public void updateWord(@RequestBody Dictionnary d,@PathVariable("idd") int idd){
		dictionnaryService.updateDictionnary(idd, d);
	}
	
	@DeleteMapping("/delete-word/{idd}")
	@ResponseBody
	public void deleteWord(@PathVariable("idd") int idd){
		dictionnaryService.deleteDictionnary(idd);
	}
	
}

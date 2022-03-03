package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
	
}

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

//import io.swagger.annotations.Api;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.services.*;
@RestController

@RequestMapping("/Feedback")
public class FeedbackController {
	@Autowired

	IFeedbackSERVICE FeedbackService;
		
	@PostMapping("/add-Feedback")
	@ResponseBody
	public void addFeedbacks(@RequestBody List<? extends Feedback> Feedbacks){
		FeedbackService.addFeedbacks(Feedbacks);
	}

	@PutMapping("/UpdateFeedback/{idF}/{nameF}/{comment}/{type}")
	@ResponseBody
	public void UpdateFeedback (@PathVariable ("idF") int idF,@PathVariable("nameF") String nameF,@PathVariable ("comment") String comment,@PathVariable ("type") FeedbackType type)
	{
		FeedbackService.UpdateFeedback(idF, nameF, comment, type);
	}
	
	@GetMapping("/Feedbacks")
	@ResponseBody
	public List<Feedback> getAllFeedbacks(){
		return FeedbackService.getAllFeedbacks();
	}
	
	@DeleteMapping("/SupprimerFeedback/{idF}")
	@ResponseBody
	public void supprimerFeedback(Feedback feedback,@PathVariable("idF")int idF )
		{
		FeedbackService.deleteFeedback(feedback, idF);
		}
	
	 @GetMapping("/ByUser/{id}")
	 @ResponseBody
	 public  int getNbreFeeddbacksByUser(@PathVariable("id") long id){
		return FeedbackService.nbrefeedbacktByUser(id);
	}
	 
	 @GetMapping("/ByUserActif/{id}")
	 @ResponseBody
	 public  int getNbreFeeddbacksActifByUser(@PathVariable("id") long id){
		return FeedbackService.nbrefeedbackActifByUser(id);
	}
	 @GetMapping("/ByUserEvaluatif/{id}")
	 @ResponseBody
	 public  int getNbreFeeddbacksevaluatifByUser(@PathVariable("id") long id){
		return FeedbackService.nbrefeedbackevaluatifByUser(id);
	}
	 
	 @GetMapping("/satisfactionUser/{id}")
	 @ResponseBody
	 public  String satisfactionUser(@PathVariable("id") long id){
		return FeedbackService.satisfactionUser(id);
	}
	 
	 @PutMapping(value = "/BlockFeedback/{id}")
	@ResponseBody
	public String BlockFeedback(@PathVariable int id)  {
			 return FeedbackService.BlockFeedback(id);
	}	
	 //recherche
	// @GetMapping("/search-Feedback/{idu}")
		//@ResponseBody
		//List<Message> searchMessages(@RequestParam String m, @PathVariable("idu") long idu){
			//return FeedbackService.searchFeedback(m,idu);
		//}	
	 @GetMapping("/search-Feedback/{idu}")
	 @ResponseBody
	 List<Feedback> searchFeedback(@RequestParam String m, @PathVariable("idu") long idu){
	 return FeedbackService.searchFeedback(m,idu);
	 }
	 
	
}
package tn.esprit.spring.controllers;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.services.*;

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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@RestController
@Api(tags = "Complaint")
@RequestMapping("/complaint")
public class ComplaintController {
 @Autowired

	IComplaintSERVICE ComplaintService;
 
 //Ajout
    @ApiOperation(value = "add a complaint")
	@PostMapping("/add-complaint/{idu}")
	@ResponseBody
	public void addComplaints(@RequestBody Complaint c,@PathVariable("idu") long idu){
		ComplaintService.addComplaint(c,idu);
	}
	
	//Update
	@PutMapping("/UpdateComplaint/{idC}/{nameC}/{description}/{processing}")
	@ResponseBody
	public void UpdateComplaint (@PathVariable ("idC") int idC,@PathVariable("nameC") String nameC,@PathVariable ("description") String description,@PathVariable ("processing") Processing processing)
	{
		ComplaintService.UpdateComplaints(idC, nameC, description, processing);
	}
	
	//affichage
	@GetMapping("/Complaints")
	@ResponseBody
	public List<Complaint> getAllComplaints(){
		return ComplaintService.getAllComplaints();
	}
	
	//suppression
	@DeleteMapping("/SupprimerComplaint/{idF}")
	@ResponseBody
	public void supprimerComplaint(Complaint Complaint,@PathVariable("idC")int idC )
		{
		ComplaintService.deleteComplaint(Complaint, idC);
		}
	
	//Reponse par mail
	 @PostMapping("/mail/{id}")
	 @ResponseBody
	 public String mail (@PathVariable("id") int id , @RequestBody String text) {
		ComplaintService.responseByMail(id,text);
		 return "Mail Sended";
		 
	 }	 
	 
	 @GetMapping("/ByUser/{id}")
	 @ResponseBody
	 public  int getNbreComplaintsByUser(@PathVariable("id") long id){
		return ComplaintService.nbreComplaintByUser(id);
	}
	 
	 //filtrage gros mots
	 @PutMapping(value = "/BlockComplaint/{id}")
		@ResponseBody
		public String BlockComplaint(@PathVariable int id)  {
			 return ComplaintService.BlockComplaint(id);
		}
	 
	 @PostMapping("/add-listeComplaint/{User_Id}")
	 @ResponseBody
	 void ajouterEtaffecterListeComplaint(@RequestBody List<Complaint> lb,@PathVariable("User_Id") Long User_Id) {
	 ComplaintService.ajouterEtaffecterListeComplaint(lb, User_Id);
	 }
	 
}


	
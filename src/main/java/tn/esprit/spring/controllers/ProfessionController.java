
package tn.esprit.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Domain;
import tn.esprit.spring.entities.Profession;
import tn.esprit.spring.services.IDomainService;
import tn.esprit.spring.services.IProfessionService;


@RestController
public class ProfessionController
	{
		@Autowired
			IProfessionService Professionservice;
/*
		//AddProfession
		
		//http://localhost:8089/AjouterProfession
				@PostMapping("/AjouterProfession")
				@ResponseBody
					public void AjouterProfession(@ RequestBody Profession profession)
						{
					Professionservice.AddProfession(profession);
						}
		*/
		
		//Update


		//http://localhost:8089/UpdateProfession
		@PutMapping("/UpdateProfession/{ID_Profession}/{ProfessionName}")
		@ResponseBody
			public void updateuser(@PathVariable ("ID_Profession") int ID_Profession,@PathVariable ("ProfessionName") String ProfessionName )
				{
	
			Professionservice.UpdateProfession(ID_Profession,ProfessionName );
	
				}
		
		
		//Delete 
		

		//http://localhost:8089/DeleteProfession
		@DeleteMapping("/DeleteProfession/{ID_Profession}")
		@ResponseBody
		public void delete(@PathVariable("ID_Profession") int ID_Profession)
			{
			Professionservice.DeleteProfession(ID_Profession);

			}
		
		
		//@Api(tags = "User management")


	//injection de service
		
		IProfessionService ProfessionService;
		@Autowired

		
		
		//http://localhost:8089/retrieve-all-Professions
			@GetMapping("/retrieve-all-Professions")
			@ResponseBody
			public List<Profession> getProfessions() {
			List<Profession> listProfessions = Professionservice.retrieveAllProfessions();
			return listProfessions;
			}
		
		
		
			//http://localhost:8089/add-Profession
			@PostMapping("/add-Profession")
			@ResponseBody
			public String addProfession(@RequestBody Profession c)
			{
				Profession profession;
				if (TestVald(c) == false ){
					Professionservice.addProfession(c);
					return  "Profession sucessfully added";
				}
				return "Profession already exists !";
			}	
			
	
			public boolean TestVald(Profession c){
				Profession profession = (Profession) ProfessionService.findByProfessionUser(c.getProfessionName());
				if (profession == null ){
					return false;
				}
				return true ;
				
			}
			

		
		
		
	}

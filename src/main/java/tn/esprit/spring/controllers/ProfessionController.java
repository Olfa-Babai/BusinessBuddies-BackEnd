
package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Profession;
import tn.esprit.spring.services.IProfessionService;


@RestController
public class ProfessionController
	{
		@Autowired
			IProfessionService Professionservice;

		//AddProfession
		
		//http://localhost:8089/AjouterProfession
				@PostMapping("/AjouterProfession")
				@ResponseBody
					public void AjouterProfession(@ RequestBody Profession profession)
						{
					Professionservice.AddProfession(profession);
						}
		
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

		
		
		
	}

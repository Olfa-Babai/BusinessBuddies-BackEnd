
package tn.esprit.spring.services;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;


import tn.esprit.spring.entities.Profession;
import tn.esprit.spring.entities.User;

public interface IProfessionService
	{
	
	 public void addProfession(@RequestBody Profession c);
		public void UpdateProfession (int ID_Profession,String ProfessionName);
		public void DeleteProfession (int ID_Profession);
		Profession retrieveProfession(int ID_Profession);
		List<User> findByProfessionUser(String ProfessionName);
		public List<Profession> retrieveAllProfessions();
	}

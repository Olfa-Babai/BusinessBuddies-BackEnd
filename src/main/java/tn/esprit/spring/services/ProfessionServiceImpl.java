package tn.esprit.spring.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Profession;
import tn.esprit.spring.repositories.ProfessionRepository;

@Service
public class ProfessionServiceImpl implements IProfessionService{

	
	@Autowired
	ProfessionRepository Prorepo;


public ProfessionRepository getProrepo() {
		return Prorepo;
	}


	public void setProrepo(ProfessionRepository prorepo) {
		Prorepo = prorepo;
	}


//Add 
	
public void AddProfession(Profession Profession) 
		{
	Prorepo.save(Profession);
			
		}


//Update 

public void UpdateProfession(int ID_Profession, String ProfessionName) 
	{
	
	Profession profession = Prorepo.findById(ID_Profession).orElse(null);
	profession.setProfessionName(ProfessionName);
		Prorepo.save(profession);
	}


//Delete

public void DeleteProfession(int ID_Profession) 
	{
	
	Prorepo.deleteById(ID_Profession);
		
	}


}


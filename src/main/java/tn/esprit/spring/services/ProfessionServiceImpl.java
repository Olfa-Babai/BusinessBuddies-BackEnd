package tn.esprit.spring.services;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Domain;
import tn.esprit.spring.entities.Profession;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repositories.ProfessionRepository;
import tn.esprit.spring.repositories.UserRepository;

@Service
public class ProfessionServiceImpl implements IProfessionService{

	
	@Autowired
	ProfessionRepository Prorepo;
	
	@Autowired
	UserRepository userRepository;


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

@Override
public Profession retrieveProfession(int ID_Profession){
	Profession d = Prorepo.findById(ID_Profession).get();
	return d;
}

public List<Profession> retrieveAllProfessions(){
	return (List<Profession>) Prorepo.findAll();
}

@Override
public List<User> findByProfessionUser(String ProfessionName) {
	List<User> users=(List<User>) userRepository.findAll();
	List<User> newUsers=new ArrayList<User>();
	for(User d : users) {
		if(d.getProfession().getProfessionName().equals(ProfessionName)){
			newUsers.add(d);
		}
	}
	return newUsers;
}


@Override
public void addProfession(Profession Profession) { 
	{
             Prorepo.save(Profession);
		
	}
	
}



}


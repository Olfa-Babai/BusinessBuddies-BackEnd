package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Dictionnary;

public interface IDictionnaryService {

	public String wordsChecked(String s);

	void addDictionnary(Dictionnary d);

	void updateDictionnary(int idd, Dictionnary d);

	void deleteDictionnary(int idd);
	List<Dictionnary> listall();
	
}

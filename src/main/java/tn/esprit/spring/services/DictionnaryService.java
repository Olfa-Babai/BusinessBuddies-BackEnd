package tn.esprit.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Dictionnary;
import tn.esprit.spring.repositories.DictionnaryRepository;

@Service
public class DictionnaryService implements IDictionnaryService {

	@Autowired 
	DictionnaryRepository dictionnaryRepository;
	
	@Override
	public String wordsChecked(String s) {
		for(Dictionnary d : dictionnaryRepository.findAll()){
			if (s.toLowerCase().contains(d.getWord().toLowerCase())){
				String ch="";
			for(int i=1;i<=d.getWord().length();i++){
				ch+="*";
			}
			s=s.replace(d.getWord(), ch);
			}
		}
		return s;
	}
	
	@Override
	public void addDictionnary(Dictionnary d){
		dictionnaryRepository.save(d);
	}
	
	@Override
	public void updateDictionnary(int idd,Dictionnary d){
		Dictionnary dy=dictionnaryRepository.getById(idd);
		dy.setWord(d.getWord());
	}
	
	@Override 
	public void deleteDictionnary(int idd){
		dictionnaryRepository.delete(dictionnaryRepository.getById(idd));
	}

	@Override
	public List<Dictionnary> listall() {
		return dictionnaryRepository.findAll();
	}

}

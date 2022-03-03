package tn.esprit.spring.services;

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
			if (s.contains(d.getWord())){
				String ch="";
			for(int i=1;i<=d.getWord().length();i++){
				ch+="*";
			}
			s=s.replace(d.getWord(), ch);
			}
		}
		return s;
	}

}

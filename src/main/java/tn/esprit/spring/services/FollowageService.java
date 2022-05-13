package tn.esprit.spring.services;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.repositories.*;
import tn.esprit.spring.entities.*;

@Service
public class FollowageService implements IFollowageService {

	@Autowired
	FollowageRepository followageRepository;
	
	@Autowired 
	UserRepository userRepository;

	@Override
	public User verifyingUser(String u){
		return userRepository.findByUsername(u).get();
	}
	
	@Override
	public void addFollow(long idUser, Followage followage) {
		User u=userRepository.findById(idUser).orElse(null);
		followage.setFollower(u);
		followageRepository.save(followage);
	}

	@Override
	public void unfollow(int idf) {
		followageRepository.delete(followageRepository.findById(idf).get());
	}
	
	@Override
	public List<Followage> followingsOfU(long idu) {
		User u=userRepository.findById(idu).get();
		return u.getFollowings();
	}

	
	@Override
	public List<User> followersTheme(String theme){
		List<User> fs=new ArrayList<User>();				
		for(Followage f : followageRepository.findAll()){
			if(f.getTheme().toString().equals(theme))
				fs.add(f.getFollower());
		}
		return fs;
	}
	
	@Override
	public List<String> searchTheme(String theme){
		List<String> thems=new ArrayList<String>();
		Theme[] themes=Theme.values();
		for(Theme t: themes){
		if(t.toString().toLowerCase().contains(theme.toLowerCase())){
			thems.add(t.name());
		}
		}
		return thems;
	}
	
	@Override
	public Followage bestRatedThemeUser(long idu){
		User u=userRepository.findById(idu).get();		
		return u.getFollowings().stream().max(Comparator.comparing(Followage::getRating)).get();
	}
	
	@Override
	public String bestRatedTheme(){
		int max=0;
		Theme theme;
		System.out.println("");
		HashMap<Theme,Integer> comp=new HashMap<Theme,Integer>();
		Theme[] themes=Theme.values();
		for(Theme t: themes){
			int nb=0;
			for(Followage f : followageRepository.findAll()){
				if (f.getTheme().equals(t)){
					nb=nb+f.getRating();
				}
			}
			comp.put(t,nb);
		}
		theme = Collections.max(comp.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
		return theme.toString();
	}
	
	@Override
	public List<Theme> sortThemes(String sort){
		List<Theme> themes= new ArrayList<Theme>();
		for (Theme t : Theme.values()){
			themes.add(t);
		}
		if(sort.equals("desc"))
			return themes.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());
		else
			return themes.stream().sorted().collect(Collectors.toList());
	}
	
}

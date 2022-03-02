package tn.esprit.spring.services;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
	public Followage bestRatedThemeUser(long idu){
		User u=userRepository.findById(idu).get();		
		return u.getFollowings().stream().max(Comparator.comparing(Followage::getRating)).get();
	}
	
	/*@Override
	public String bestRatedAll(){
		List<String> fs=followageRepository.searchMaxRatedTheme();
		return fs.toString();
	}*/
}

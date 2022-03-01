package tn.esprit.spring.services;


import java.util.ArrayList;
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
		u.getFollows().add(followage);
		followage.getFollowers().add(u);
		followageRepository.save(followage);
	}

	@Override
	public void unfollow(int idf) {
		followageRepository.delete(followageRepository.findById(idf).get());
	}
	
	@Override
	public List<Followage> followersU(long idu) {
		User u=userRepository.findById(idu).get();
		return u.getFollows();
	}

	@Override
	public Followage findByThemeAndUser(long idu, String theme) {
		for(Followage f : followersU(idu)){
			if(f.getTheme().toString().equals(theme)){
				return f;
			}
		}
		return null;
	}
	
	
	
}

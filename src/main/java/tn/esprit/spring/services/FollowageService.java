package tn.esprit.spring.services;


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
		followage.getFollowers().add(userRepository.findById(idUser).orElse(null));
		followageRepository.save(followage);	
	}

	@Override
	public void unfollow(Followage followage) {
		followageRepository.delete(followage);
	}
	
	
}

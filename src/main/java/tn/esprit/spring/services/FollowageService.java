package tn.esprit.spring.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.repositories.FollowageRepository;
import tn.esprit.spring.entities.*;

@Service
public class FollowageService implements IFollowageService {

	@Autowired
	FollowageRepository followageRepository;

	@Override
	public void addFollow(int idUser, Followage followage) {
		// followage.setFollower(userRepository.getById(idUser));
		followageRepository.save(followage);	
	}

	@Override
	public void unfollow(Followage followage) {
		followageRepository.delete(followage);
	}
	
	
}

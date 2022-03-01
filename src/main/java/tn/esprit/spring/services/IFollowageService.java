package tn.esprit.spring.services;

import tn.esprit.spring.entities.Followage;

public interface IFollowageService {
	void addFollow(int idUser, Followage followage);
	void unfollow(Followage followage);
}

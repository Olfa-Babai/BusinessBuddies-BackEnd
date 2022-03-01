package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Followage;

public interface IFollowageService {
	void addFollow(long idUser, Followage followage);
	List<Followage> followersU(long idu);
	void unfollow(int idf);
	Followage findByThemeAndUser(long idu, String theme);
}

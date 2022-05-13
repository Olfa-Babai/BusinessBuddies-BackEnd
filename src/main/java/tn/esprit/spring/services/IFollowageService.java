package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Followage;
import tn.esprit.spring.entities.Theme;
import tn.esprit.spring.entities.User;

public interface IFollowageService {
	void addFollow(long idUser, Followage followage);
	void unfollow(int idf);
	List<Followage> followingsOfU(long idu);
	List<User> followersTheme(String theme);
	Followage bestRatedThemeUser(long idu);
	String bestRatedTheme();
	List<String> searchTheme(String theme);
	List<Theme> sortThemes(String sort);
	User verifyingUser(String u);
}

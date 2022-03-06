package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Like;
import tn.esprit.spring.entities.Theme;

public interface ILikeService {	
	
	void addLike(int idp,long idu, Like like);
	void removeLike(int idl);
	List<Like> showPostsLikes(int idp);
	List<Like> showPostsDislikes(int idp);
	Theme mostLikedPostsTheme(long idu);
	
}

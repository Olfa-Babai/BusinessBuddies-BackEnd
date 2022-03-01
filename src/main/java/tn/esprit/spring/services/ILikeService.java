package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Like;

public interface ILikeService {	
	
	void addLike(int idp, Like like);
	void removeLike(int idl);
	List<Like> showPostsLikes(int idp);
	List<Like> showPostsDislikes(int idp);
	
}

package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.*;

public interface IPostService {

	void addPost(Post p);
	void updatePost(Post p, int idp);
	void deletePost(int idp);
	void showPostsByUser(Post p, int idu);
	List<Post> showPostsByTheme(String t);
	List<Post> showAllPosts();
	List<Post> searchPosts(String p, String t);
	
	// research + filter
	
}

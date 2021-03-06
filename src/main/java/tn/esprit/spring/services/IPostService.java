package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.*;

public interface IPostService {

	void addPost(long idu,Post p);
	void updatePost(Post p, int idp);
	void deletePost(int idp);
	List<Post> showPostsByUser(long idu);
	List<Post> showPostsByTheme(String t);
	List<Post> showAllPosts();
	List<Post> searchPosts(String p, String t);
	List<Post> recommendedPosts(long idu);
	List<Post> showPostsByUserNTheme(String theme, long idu);
	int ratingstheme(String theme);
	int interactiontheme(String theme);
	
	// research + filter
	
}

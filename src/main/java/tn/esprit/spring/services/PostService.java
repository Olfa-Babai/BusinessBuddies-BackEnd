package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Post;
import tn.esprit.spring.repositories.PostRepository;

@Service
public class PostService implements IPostService {

	@Autowired
	PostRepository postRepository;

	@Override
	public void addPost(Post p) {
		postRepository.save(p);
	}

	@Override
	public void updatePost(Post p, int idp) {
		postRepository.save(p);		
	}

	@Override
	public void deletePost(int idp) {
		postRepository.delete(postRepository.findById(idp).orElse(null));
	}

	@Override
	public void showPostsByUser(Post p, int idu) {
	// show posts by user
	}

	@Override
	public List<Post> showPostsByTheme(String theme) {
		List<Post> allposts= (List<Post>) postRepository.findAll();
		List<Post> posts=new  ArrayList<Post>();
		for(Post post : allposts){
			if (post.getTheme().equals(theme)){
				posts.add(post);
				}
		}
		return posts;
	}
	
	@Override
	public List<Post> showAllPosts() {
		return (List<Post>) postRepository.findAll();
	}
	
	@Override
	public List<Post> searchPosts(String s, String t) {
		List<Post> posts=new ArrayList<Post>();
		List<Post> allposts=(List<Post>)postRepository.findAll();
		for(Post p : allposts){
			if (p.getBody().equals(s) || p.getTitle().equals(s) && p.getTheme().equals(t)) {
			posts.add(p);
			}			
		}
		return posts;
	}
	
}

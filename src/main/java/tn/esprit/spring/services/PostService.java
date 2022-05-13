package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Followage;
import tn.esprit.spring.entities.Like;
import tn.esprit.spring.entities.Post;
import tn.esprit.spring.entities.Theme;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repositories.*;

@Service
public class PostService implements IPostService {

	@Autowired
	PostRepository postRepository;
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	IFollowageService followageService;
	
	@Autowired
	FollowageRepository fr;
	
	@Autowired
	ILikeService likeService;
	
	@Override
	public void addPost(long idu,Post p) {
		p.setPublisher(userRepository.findById(idu).get());
		postRepository.save(p);
	}

	@Override
	public void updatePost(Post p, int idp) {
		Post post=postRepository.findById(idp).get();
		post.setTitle(p.getTitle());
		post.setBody(p.getBody());
		post.setTheme(p.getTheme());
		postRepository.save(post);	
	}

	@Override
	public void deletePost(int idp) {
		postRepository.delete(postRepository.findById(idp).get());
	}

	@Override
	public List<Post> showPostsByUserNTheme(String theme, long idu) {
		User u=userRepository.findById(idu).get();
		List<Post> posts=u.getPosts();
		List<Post> result=new ArrayList<Post>();
		for(Post p : posts){
			if (p.getTheme().toString().equals(theme)){
				result.add(p);
			}
		}
		return result;
	}
	
	@Override
	public List<Post> showPostsByUser(long idu) {
		User u=userRepository.findById(idu).get();
		return u.getPosts();
	}

	@Override
	public List<Post> showPostsByTheme(String theme) {
		List<Post> allposts= postRepository.findAll();
		List<Post> posts=new  ArrayList<Post>();
			for(Post post : allposts){
				if (post.getTheme().toString().equals(theme)){
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
		for(Post p : showPostsByTheme(t)){
			if (p.getBody().toLowerCase().contains(s.toLowerCase()) || p.getTitle().toLowerCase().contains(s.toLowerCase())) {
			posts.add(p);
			}			
		}
		return posts;
	}
	
	public List<Post> postsNotLikedNotPosted(String theme,long idu){
		List<Post> posts=new ArrayList<Post>();
		boolean test=false;
		for(Post p : showPostsByTheme(theme)){
			if( !(p.getPublisher().equals(userRepository.findById(idu))) ){
				for(Like l : p.getLikes()){
					if (l.getLiker().equals(userRepository.findById(idu)))
					{
						test=true;
					}
					if(test==false) posts.add(p);
				}
			}
		}
		return posts;
	}
	
	@Override
	public List<Post> recommendedPosts(long idu){
		List<Post> posts=new ArrayList<Post>();
		User u=userRepository.findById(idu).get();
		Theme theme1=followageService.bestRatedThemeUser(idu).getTheme();
		Theme theme2=likeService.mostLikedPostsTheme(idu);
		
		if(theme1.equals(theme2) && !(theme1.equals(null))){
			posts=postsNotLikedNotPosted(theme1.toString(), idu);
			posts=posts.stream().limit(2).collect(Collectors.toList());
		}
		else if(!(theme2.equals(theme1)) &&!(theme1.equals(null)) && !(theme2.equals(null)) ) {
			posts.addAll(postsNotLikedNotPosted(theme1.toString(), idu));
			posts=posts.stream().limit(2).collect(Collectors.toList());
			posts.addAll(postsNotLikedNotPosted(theme2.toString(), idu));
			posts=posts.stream().limit(4).collect(Collectors.toList());
		}
		else if( !(theme2.equals(theme1)) && (theme1.equals(null)) && !(theme2.equals(null)) ){
			posts.addAll(postsNotLikedNotPosted(theme2.toString(), idu));
			posts=posts.stream().limit(2).collect(Collectors.toList());	
		}
		else if( !(theme2.equals(theme1)) && !(theme1.equals(null)) && (theme2.equals(null)) ){
			List<Post> postes=postRepository.findAll();
			if(showPostsByUser(idu).equals(null)){
				posts.addAll(postes);
				posts=posts.stream().limit(2).collect(Collectors.toList());	
			}else
			postes.removeAll(showPostsByUser(idu));
			posts.addAll(postes);
			posts=posts.stream().limit(2).collect(Collectors.toList());	
		}
		else if((theme1.equals(null)) && (theme2.equals(null))) {
			List<Post> postes=postRepository.findAll();
			if(showPostsByUser(idu).equals(null)){
				posts.addAll(postes);
				posts=posts.stream().limit(2).collect(Collectors.toList());	
			}else
			postes.removeAll(showPostsByUser(idu));
			posts.addAll(postes);
			posts=posts.stream().limit(2).collect(Collectors.toList());	
		}
		return posts;
	}

	@Override
	public int ratingstheme(String theme) {
		int s=0;
		for(Followage f : fr.findAll()){
			if(f.getTheme().toString().equals(theme)){
				s=s+f.getRating();
			}
		}
		return s;
	}

	@Override
	public int interactiontheme(String theme) {
		return this.showPostsByTheme(theme).size();
	}
	
}

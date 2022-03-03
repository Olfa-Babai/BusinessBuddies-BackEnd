package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Like;
import tn.esprit.spring.entities.Post;
import tn.esprit.spring.entities.TypeLike;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repositories.LikeRepository;
import tn.esprit.spring.repositories.PostRepository;
import tn.esprit.spring.repositories.UserRepository;

@Service
public class LikeService implements ILikeService {

	@Autowired
	LikeRepository likeRepository;
	
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired 
	IPostService postService;

	@Override
	public void addLike(int idp,long idu, Like like) {
		for(Like l : likeRepository.findAll()){
			if(l.getLiker().getUser_Id()==idu && l.getPost().getIdpost()==idp){
				removeLike(l.getIdlike());
			}
		}
		like.setPost(postRepository.getById(idp));
		like.setLiker(userRepository.getById(idu));
		likeRepository.save(like);
	}

	@Override
	public void removeLike(int idl) {
		likeRepository.delete(likeRepository.findById(idl).get());
	}

	@Override
	public List<Like> showPostsLikes(int idp) {
		List<Like> likes=new ArrayList<Like>();
		for(Like l : likeRepository.findAll()){
			if (l.getType().equals(TypeLike.like) && l.getPost().getIdpost()==idp){
				likes.add(l);
			}
		}		
		return likes;
	}

	@Override
	public List<Like> showPostsDislikes(int idp) {
		List<Like> dislikes=new ArrayList<Like>();
		for(Like l : likeRepository.findAll()){
			if (l.getType().equals(TypeLike.dislike) && l.getPost().getIdpost()==idp){
				dislikes.add(l);
			}
		}		
		return dislikes;
	}
	
	@Override
	public List<Post> mostLikedPosts(String theme){
		TreeMap<Integer,Integer> ints=new TreeMap<Integer,Integer>();
		for(Post p : postService.showPostsByTheme(theme)){
			List<User> likes=new ArrayList<User>();
			for(Like l : p.getLikes()){
				if (l.getType().equals(TypeLike.like)){
					likes.add(l.getLiker()); }
			ints.put(p.getIdpost(), likes.size());
			}
		}
		ints.entrySet().stream().sorted(Map.Entry.comparingByValue());
		List<Post> posts=new ArrayList<Post>();
		List<Integer> idposts=new ArrayList<Integer>(ints.keySet());
		for(int i : idposts){
			posts.add(postRepository.getById(i));
		}
		return posts;
	}
}

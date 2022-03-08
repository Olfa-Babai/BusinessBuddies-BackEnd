package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Like;
import tn.esprit.spring.entities.Post;
import tn.esprit.spring.entities.Theme;
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
		like.setLiker(userRepository.findById(idu).get());
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
	public Theme mostLikedPostsTheme(long idu){
		User u=userRepository.findById(idu).get();
		HashMap<Theme,Integer> comp=new HashMap<Theme,Integer>();
		Theme[] themes=Theme.values();
		for(Theme t: themes){
			int nb=0;
			for(Like l : u.getLikes()){
				if((l.getPost().getTheme().equals(t)) && (l.getType().equals(TypeLike.like))){
					nb++;
				}
			}
			comp.put(t, nb);
		}
		return Collections.max(comp.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
	}
	
	@Override
	public Post mostLikedPost(String theme){
		int max=0;
		Post post=new Post();
		for(Post p : postService.showPostsByTheme(theme)){
			List<Like> likes=new ArrayList<Like>();
			for(Like l : p.getLikes()){
				if(l.getType().equals(TypeLike.like)){
				likes.add(l);
				}
				}
			int nb=likes.size();
			if(nb>max){
				max=nb;
				post=p;
			}
		}
		return post;
	}
}

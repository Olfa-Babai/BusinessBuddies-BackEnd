package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Like;
import tn.esprit.spring.entities.TypeLike;
import tn.esprit.spring.repositories.LikeRepository;
import tn.esprit.spring.repositories.PostRepository;

@Service
public class LikeService implements ILikeService {

	@Autowired
	LikeRepository likeRepository;
	
	@Autowired
	PostRepository postRepository;

	@Override
	public void addLike(int idp, Like like) {
		like.setPost(postRepository.getById(idp));
		likeRepository.save(like);
	}

	@Override
	public void removeLike(int idl) {
		likeRepository.delete(likeRepository.findById(idl).orElse(null));
	}

	@Override
	public List<Like> showPostsLikes(int idp) {
		List<Like> alllikes=(List<Like>)likeRepository.findAll();
		List<Like> likes=new ArrayList<Like>();
		for(Like l : alllikes){
			if (l.getType().equals(TypeLike.like) && l.getPost().getIdpost()==idp){
				likes.add(l);
			}
		}		
		return likes;
	}

	@Override
	public List<Like> showPostsDislikes(int idp) {
		List<Like> alllikes=(List<Like>)likeRepository.findAll();
		List<Like> dislikes=new ArrayList<Like>();
		for(Like l : alllikes){
			if (l.getType().equals(TypeLike.dislike) && l.getPost().getIdpost()==idp){
				dislikes.add(l);
			}
		}		
		return dislikes;
	}
	
}

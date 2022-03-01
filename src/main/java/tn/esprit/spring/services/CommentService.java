package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Comment;
import tn.esprit.spring.repositories.CommentRepository;
import tn.esprit.spring.repositories.PostRepository;

@Service
public class CommentService implements ICommentService {

	@Autowired
	CommentRepository commentRepository;

	@Autowired
	PostRepository postRepository;
	
	@Override
	public void addComment(Comment c, int idp) {
		c.setPost(postRepository.findById(idp).orElse(null));
		commentRepository.save(c);
	}

	@Override
	public void updateComment(Comment c, int idc) {
		if(containsComment(idc))
			commentRepository.save(c);
	}

	@Override
	public void deleteComment(int idc) {
		if(containsComment(idc))
			commentRepository.delete(commentRepository.getById(idc));
	}

	@Override
	public List<Comment> viewCommentsByPost(int idpost) {
		List<Comment> commentsall= (List<Comment>) commentRepository.findAll();
		List<Comment> comments= new ArrayList<Comment>();
		for(Comment c : commentsall){
			if(c.getPost().getIdpost()==idpost){
				comments.add(c);
			}
		}
		return comments;
	}

	@Override
	public Comment getCommentById(int idc) {
		return commentRepository.findById(idc).orElse(null);
	}
	
	public boolean containsComment(int idc){
		return commentRepository.findById(idc).orElse(null)!=null;
	}

	@Override
	public List<Comment> recentCommentsByPost(int idpost) {
		return commentRepository.findAll(Sort.by(Direction.DESC,"published"));
	}
	
}

package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Comment;
import tn.esprit.spring.entities.Post;
import tn.esprit.spring.repositories.CommentRepository;
import tn.esprit.spring.repositories.PostRepository;
import tn.esprit.spring.repositories.UserRepository;

@Service
public class CommentService implements ICommentService {

	@Autowired
	CommentRepository commentRepository;

	@Autowired
	PostRepository postRepository;
	
	@Autowired 
	PostService postService;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public void addComment(Comment c, int idp, long idu) {
		c.setPost(postRepository.findById(idp).get());
		c.setCommenter(userRepository.findById(idu).get());
		commentRepository.save(c);
	}

	@Override
	public void updateComment(Comment c, int idc) {
			Comment comment=commentRepository.findById(idc).get();
			comment.setBody(c.getBody());
			comment.setPublished(c.getPublished());
			commentRepository.save(comment);
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
	
	@Override
	public Post mostCommentedPost(String theme){
			List<Post> allposts=postService.showPostsByTheme(theme);
			Post selected=new Post();
			int max=0;
			for(Post p : allposts){
				if(p.getComments().size()>max){
					max=p.getComments().size();
					selected=p;
				}
			}
			return selected;
		}
	
}

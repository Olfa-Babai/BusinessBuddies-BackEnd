package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Comment;
import tn.esprit.spring.entities.Post;

public interface ICommentService {

	void addComment(Comment c,int idp, long idu);
	void updateComment(Comment c, int idc);
	void deleteComment(int idc);
	List<Comment> viewCommentsByPost(int idpost);
	List<Comment> recentCommentsByPost(int idpost);
	Comment getCommentById(int idc);
	Post mostCommentedPost(String theme);
}

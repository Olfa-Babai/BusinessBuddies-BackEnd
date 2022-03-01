package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Comment;

public interface ICommentService {

	void addComment(Comment c,int idp);
	void updateComment(Comment c, int idc);
	void deleteComment(int idc);
	List<Comment> viewCommentsByPost(int idpost);
	List<Comment> recentCommentsByPost(int idpost);
	Comment getCommentById(int idc);
}

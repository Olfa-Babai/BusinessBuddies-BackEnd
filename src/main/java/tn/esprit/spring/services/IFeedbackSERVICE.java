package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.*;

public interface IFeedbackSERVICE {

	void addFeedbacks(List<? extends Feedback> feedbacks);

	void UpdateFeedback(int idF, String nameF, String comment, FeedbackType type);

	List<Feedback> getAllFeedbacks();

	void deleteFeedback(Feedback feedback, int idF);

	int nbrefeedbacktByUser(Long idU);

	int nbrefeedbackActifByUser(Long idU);

	int nbrefeedbackevaluatifByUser(Long idU);

	String satisfactionUser(long idU);

	String BlockFeedback(int idF);

	List<Feedback> searchFeedback(String s, long idu);

	List<Feedback> nbFeedbacksEvaluative(long idu);

	List<Feedback> nbFeedbacksActif(long idu);

	
	
	
}
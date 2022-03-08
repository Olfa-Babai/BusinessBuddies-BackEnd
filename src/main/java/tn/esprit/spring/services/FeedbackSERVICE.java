package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.*;
import tn.esprit.spring.repositories.*;

@Service
public class FeedbackSERVICE implements IFeedbackSERVICE{
	@Autowired
	IFeedback FeedbackRepository;
	@Autowired
	IUser userRepository;
	@Autowired 
	IComplaintSERVICE complaintService ;
	@Override
	public void addFeedbacks(List<? extends Feedback> feedbacks) {
		FeedbackRepository.saveAll(feedbacks);
	}
	
	@Override 
	public List<Feedback> getAllFeedbacks(){
		return (List<Feedback>) FeedbackRepository.findAll();
	}
	@Override
	public void UpdateFeedback(int idF, String nameF, String comment, FeedbackType type)
	{
		Feedback feedback = FeedbackRepository.findById(idF).orElse(null);
		feedback.setNameF(nameF);
		feedback.setComment(comment);
		feedback.setType(type);
		FeedbackRepository.save(feedback);
	}
	
	
	@Override
	public void deleteFeedback(Feedback feedback,int idF) {
		FeedbackRepository.deleteById(idF);
		
	}
	@Override
	public int nbrefeedbacktByUser (Long idU) {
		List<Feedback> wlls = new ArrayList<>();
		int nb = 0 ;
		User user = userRepository.findById(idU).get();
		wlls=(List<Feedback>)FeedbackRepository.findAll();
		for (Feedback wll : wlls) {
		if(wll.getUsers() == user)
			{
			nb=nb+1;
			  
		}
			
	}
	return nb;
	}
	
	@Override
	public int nbrefeedbackActifByUser (Long idU) {
		List<Feedback> wlls = new ArrayList<>();
		int nb = 0 ;
		User user = userRepository.findById(idU).get();
		wlls=(List<Feedback>)FeedbackRepository.findAll();
		for (Feedback wll : wlls) {
		if(wll.getUsers() == user && wll.getKind().equals(FeedBacksKinds.actif))
			{
			nb=nb+1;
			  
		}
			
	}
	return nb;
	}
	@Override
	public int nbrefeedbackevaluatifByUser (Long idU) {
		List<Feedback> wlls = new ArrayList<>();
		int nb = 0 ;
		User user = userRepository.findById(idU).get();
		wlls=(List<Feedback>)FeedbackRepository.findAll();
		for (Feedback wll : wlls) {
		if(wll.getUsers() == user && wll.getKind().equals(FeedBacksKinds.evaluatif))
			{
			nb=nb+1;
			  
		}
			
	}
	return nb;
	}
	@Override
	public String satisfactionUser(long idU) {
		int nb = nbrefeedbackevaluatifByUser(idU) -  nbrefeedbackActifByUser (idU) - complaintService.nbreComplaintByUser(idU) ;
		System.out.println(nb);
		if(nb==0)
		{
			return "Client neutre";
		}
		else if (nb < 0 )
		{
			return "client non satisfait" ;
		}
		else {
			return "client satisfait";
		}
	}
	@Override
	public String BlockFeedback (int idF) {
		Feedback feed  = (Feedback) FeedbackRepository.findById(idF).get();
		String[] Badwords = {"shit" ,"ew" ,"fuck"};
		String ret = null;
		String input = feed.getComment();
				
			for (String item : Badwords) {
				if (input.contains(item)) {
					feed.setBlocked(true);
					FeedbackRepository.save(feed);
					
					 ret ="Feedback blocked";
					break ;
					} 
					
					else 
					
						{feed.setBlocked(false);
						ret="Feedback clean";
						}
				}
					
		
		return ret;
		}
	
	@Override
	public List<Feedback> searchFeedback(String s, long idu) {
		List<Feedback> FB=new ArrayList<Feedback>();
		List<Feedback> FBs=(List<Feedback>)FeedbackRepository.findAll();
		for(Feedback m : FBs){
			if (m.getComment().toLowerCase().contains(s.toLowerCase()) || m.getNameF().toLowerCase().equals(s.toLowerCase()) ) {
			FBs.add(m);
						}
			}
	return FBs;
	} 
	
	@Override
	public List<Feedback> nbFeedbacksEvaluative(long idu){
	List<Feedback> fbs=new ArrayList<Feedback>();
	User u=userRepository.findById(idu).get();
	for(Feedback f : FeedbackRepository.findAll()){
		if(f.getUsers().equals(u) && f.getKind().equals(FeedBacksKinds.evaluatif)){
			fbs.add(f);
		}
	}
	return fbs;
	}
	
	@Override
	public List<Feedback> nbFeedbacksActif(long idu){
	List<Feedback> fbs=new ArrayList<Feedback>();
	User u=userRepository.findById(idu).get();
	for(Feedback f : FeedbackRepository.findAll()){
		if(f.getUsers().equals(u) && f.getKind().equals(FeedBacksKinds.actif)){
			fbs.add(f);
		}
	}
	return fbs;
	}
	
}

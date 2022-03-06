package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.*;
import tn.esprit.spring.repositories.*;
@Service
public class ComplaintSERVICE implements IComplaintSERVICE{
	
	@Autowired
	IComplaint complaintRepository;
	
	@Autowired
	MailServiceImpl mail;
	
	@Autowired
	IUser userRepository;
	
	@Override
	public void addComplaints(List<? extends Complaint> complaints) {
		complaintRepository.saveAll(complaints);
	}
	
	@Override
	public void addComplaint(Complaint c, long idu) {
		c.setUsers(userRepository.findById(idu).get());
		complaintRepository.save(c);	
	}
	
	@Override 
	public List<Complaint> getAllComplaints(){
		return (List<Complaint>) complaintRepository.findAll();
	}
	
	@Override
	public void UpdateComplaints(int idC, String nameC, String description, Processing processing)
	{
		Complaint complaint = complaintRepository.findById(idC).orElse(null);
		complaint.setNameC(nameC);
		complaint.setDescription(description);
		complaint.setProcessing(processing);
		complaintRepository.save(complaint);
	}

	@Override
	public void deleteComplaint(Complaint complaint,int idC) {
		complaintRepository.deleteById(idC);
	}
	
	@Override
	public String responseByMail(int idC , String text) {
		Complaint complaint = complaintRepository.findById(idC).orElse(null);
		String email =complaint.getUsers().getEmail();
		String body ="Good Morning "+complaint.getUsers().getUserFirstName()+" "+complaint.getUsers().getUserName()+" ," + text + "Cordialement." + "\r\n" + 
				"business buddies." ;
		String subject="Respond to your complaint";
		mail.Sendd(email,subject,body);
		complaint.setProcessing(Processing.Processed);
		complaintRepository.save(complaint);
		return "mail sended" ;
	}
	
	@Override
	public int nbreComplaintByUser (Long idU) {
		List<Complaint> wlls = new ArrayList<>();
		int nb = 0 ;
		User user = userRepository.findById(idU).get();
		wlls=(List<Complaint>)complaintRepository.findAll();
		for (Complaint wll : wlls) {
		if(wll.getUsers() == user)
			{
			nb=nb+1;  
		}
	}
	return nb;
	}
	
	@Override
	public String BlockComplaint (int idC) {
		Complaint com  = (Complaint) complaintRepository.findById(idC).get();
		String[] Badwords = {"shit" ,"fuck" ,"euw"};
		String ret = null;
		String input = com.getDescription();
		for (String item : Badwords) {
				if (input.contains(item)) {
					com.setBlocked(true);
					complaintRepository.save(com);
					 ret ="Complaint blocked";
					 break ; 
					
					} 
					else 
						{com.setBlocked(false);
						ret="Complaint clean";
						}
				}			
		return ret;
	}
	
	
}

	
		

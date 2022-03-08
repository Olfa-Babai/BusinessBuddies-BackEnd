package tn.esprit.spring.services;

import java.util.List;


import tn.esprit.spring.entities.*;

public interface IComplaintSERVICE {

	void addComplaints(List<? extends Complaint> complaints);

	void addComplaint(Complaint c, long idu);
	
	void UpdateComplaints(int idC, String nameC, String description, Processing processing);

	List<Complaint> getAllComplaints();

	void deleteComplaint(Complaint complaint, int idC);

	String responseByMail(int idC , String text);

	int nbreComplaintByUser(Long idU);

	String BlockComplaint(int idC);

	void ajouterEtaffecterListeComplaint(List<Complaint> lb, Long User_Id);

	
	
}

package tn.esprit.spring.services;

import java.util.List;

import javax.mail.MessagingException;

import tn.esprit.spring.entities.Invitation;

public interface InvitationService {
	List<Invitation> retrieveAllInvitation();
	Invitation addInvitation (Invitation invitation);
	void deleteInvitation (Long id);
	Invitation updateInvitation (Invitation invitation);
	Invitation retrieveInvitation (Long id);
	List<Invitation> retrieveAllInvitationsSorted();
	void EnvoiInvitationParEmail(Invitation invi) throws MessagingException;
	Invitation VerifyExpirationDate(Invitation invit);
	Invitation ValidationInvitation(Invitation invi,String code);
	String encryptionAlgo(String code);
	Boolean compareCodes(String codeCorrect, String code);
	List<Invitation> searchInvits(String s);
	
}

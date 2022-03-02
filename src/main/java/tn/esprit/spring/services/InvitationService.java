package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Invitation;

public interface InvitationService {
	List<Invitation> retrieveAllInvitation();
	Invitation addInvitation (Invitation invitation);
	void deleteInvitation (Long id);
	Invitation updateInvitation (Invitation invitation);
	Invitation retrieveInvitation (Long id);
}

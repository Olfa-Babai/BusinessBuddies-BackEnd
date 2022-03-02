package tn.esprit.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Invitation;
import tn.esprit.spring.repositories.InvitationRepository;


@Service
public class InvitationServiceImpl implements InvitationService{
	
	@Autowired
	private InvitationRepository invitationRepository;
	
	@Override
	public List<Invitation> retrieveAllInvitation(){
		return (List<Invitation>) invitationRepository.findAll();
	}

	@Override
	public Invitation addInvitation(Invitation invitation) {
		return invitationRepository.save(invitation);
	}

	@Override
	public void deleteInvitation(Long id) {
		invitationRepository.deleteById(id);
	}

	@Override
	public Invitation updateInvitation(Invitation invitation) {
		return invitationRepository.save(invitation);
	}

	@Override
	public Invitation retrieveInvitation(Long id) {
		return invitationRepository.findById(id).get();
	}
}

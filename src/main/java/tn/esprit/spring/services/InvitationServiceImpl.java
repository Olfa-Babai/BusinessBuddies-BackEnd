package tn.esprit.spring.services;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Invitation;
import tn.esprit.spring.entities.invitationStatus;
import tn.esprit.spring.entities.typeInvitation;
import tn.esprit.spring.repositories.InvitationRepository;


@Service
public class InvitationServiceImpl implements InvitationService{
	
	@Autowired
	private InvitationRepository invitationRepository;
	
	@Autowired
	private EmailServiceInvitationImpl EmailServiceInvit;
	
	@Override
	public List<Invitation> retrieveAllInvitation(){
		return (List<Invitation>) invitationRepository.findAll();
	}
	
	@Override
	public List<Invitation> retrieveAllInvitationsSorted(){
		List<Invitation> invis = (List<Invitation>) invitationRepository.findAll();
		Collections.sort(invis, Comparator.comparingLong(Invitation::getIdInvitation));
		return invis;
	}
	
	@Override
	public Invitation retrieveInvitation(Long id) {
		return invitationRepository.findById(id).get();
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
	
	// Envoi du code d'acces encrypté par mail
	// Il faut changer le path de la piece jointe
	@Override
	public void EnvoiInvitationParEmail(Invitation invit) throws MessagingException {
		
		if (invit.getTypeInvitation().equals(typeInvitation.COMPANY)){
			//@EventListener(ApplicationReadyEvent.class)
			EmailServiceInvit.sendEmailWithAttachment("mohamedleith.majdoub@esprit.tn",
					    "This is an invitation to join Business Buddies Here is your validation code: "+encryptionAlgo(invit.getCodeAcces()),
						"This email has an attachment",
						"C:\\Users\\user16\\Pictures\\m.jpg");
				
			}
		else if (invit.getTypeInvitation().equals(typeInvitation.TRIP)){
			EmailServiceInvit.sendEmailWithAttachment("mohamedleith.majdoub@esprit.tn"+encryptionAlgo(invit.getCodeAcces()),
				    "This is an invitation to join a trip",
					"This email has an attachment",
					"C:\\Users\\user16\\Pictures\\m.jpg");
			}
		
	}

	// Changer le statut d'une invitation a refused si la date d'expiration est depassée
	@SuppressWarnings("deprecation")
	@Override
	public Invitation VerifyExpirationDate(Invitation invit) {
		int diff;
		
		 Date today = new Date();
		 diff = today.getMinutes()-invit.getDateInvitation().getMinutes();
		 
		 if (diff >= 1){
			 invit.setInvitationStatus(invitationStatus.REFUSED);
		 }
		
		return invitationRepository.save(invit);
		
	}
	
	// Encrypt/Decrypt with a jump of 13 character
	@Override
	public String encryptionAlgo(String code) {
		// string builder is much faster and consumes less memory when iterating 
		StringBuilder sb = new StringBuilder();
		   for (int i = 0; i < code.length(); i++) {
		       char c = code.charAt(i);
		       if       (c >= 'a' && c <= 'm') c += 13;
		       else if  (c >= 'A' && c <= 'M') c += 13;
		       else if  (c >= 'n' && c <= 'z') c -= 13;
		       else if  (c >= 'N' && c <= 'Z') c -= 13;
		       sb.append(c);
		   }
		   return sb.toString();
	}
	
	// Comparer les deux codes pour verifier la validité et retourner un boolean
	@Override
	public Boolean compareCodes(String codeCorrect, String code) {
		return encryptionAlgo(codeCorrect).equals(code);
	}
	
	
	// Si le code est valide le statut de l'invitation devient accepté
	@Override
	public Invitation ValidationInvitation(Invitation invi,String code) {
		/*if (invi.getCodeAcces().equals(code)){
			invi.setInvitationStatus(invitationStatus.ACCEPTED);
		}*/
		
		if(compareCodes(invi.getCodeAcces(), code)){
			invi.setInvitationStatus(invitationStatus.ACCEPTED);
		}
		return invitationRepository.save(invi);
	}
	
}

package tn.esprit.spring.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import tn.esprit.spring.configuration.PDFGenerator;
import tn.esprit.spring.entities.Invitation;
import tn.esprit.spring.entities.invitationStatus;
import tn.esprit.spring.entities.typeInvitation;
import tn.esprit.spring.services.EmailServiceInvitationImpl;
import tn.esprit.spring.services.InvitationService;

@RestController
@RequestMapping("/invitation")
@Api(tags="Invitation Management")
public class InvitationRestController {
	
	@Autowired
	InvitationService invitationService;
	
	@GetMapping("/retrieve-all-invitations")
	@ResponseBody
	@ApiOperation(value = "Recuperer la liste des invitations")
	public List<Invitation> getInvitations() {
		List<Invitation> listInvitation = invitationService.retrieveAllInvitation();
		return listInvitation;
	}
	
	@GetMapping("/retrieve-all-invitations-asc")
	@ResponseBody
	@ApiOperation(value = "Recuperer la liste des invitations ordonnées")
	public List<Invitation> getInvitationsAsc() {
		List<Invitation> listInvitation = invitationService.retrieveAllInvitationsSorted();
		return listInvitation;
	}
	
	@GetMapping("/retrieve-invitation/{invitation-id}/")
	@ApiOperation(value = "Recuperer une invitation par id")
	@ResponseBody
	public Invitation retrieveInvitationById(@PathVariable("invitation-id") Long invitationId) {
		return invitationService.retrieveInvitation(invitationId);
	}
	
	@GetMapping(value="/retrieve-invitations-to-pdf",
			 produces = MediaType.APPLICATION_PDF_VALUE)
	@ResponseBody
	@ApiOperation(value = "Recuperer la liste des invitations dans un pdf")
	public ResponseEntity<InputStreamResource> getInvitationsToPDF() throws IOException{
		
		List<Invitation> listInvitation = invitationService.retrieveAllInvitation();
		
		ByteArrayInputStream bis = PDFGenerator.customerPDFReport(listInvitation);
		
		
		 HttpHeaders headers = new HttpHeaders();
		 // This is an important fix to add the download Button
	        headers.add("Content-Disposition", "attachment; filename=invitations.pdf");
	        
	        return ResponseEntity
	                .ok()
	                .headers(headers)
	                .contentType(MediaType.APPLICATION_PDF)
	                .body(new InputStreamResource(bis));
	}
	
	
	@PostMapping("/add-invitation")
	@ApiOperation(value = "Ajouter une invitation")
	@ResponseBody
	public Invitation addInvitation(@RequestBody Invitation invitation) throws MessagingException
	{
		invitation.setInvitationStatus(invitationStatus.PENDING);
		Invitation invit = invitationService.addInvitation(invitation);
		invitationService.EnvoiInvitationParEmail(invit);
		return invit;
	}
	
	@DeleteMapping("/remove-invitation/{invitation-id}")
	@ApiOperation(value = "Supprimer une invitation")
	@ResponseBody
	public void removeInvitation(@PathVariable("invitation-id") Long invitationId) {
		invitationService.deleteInvitation(invitationId);
	}
	
	@PutMapping("/modify-invitation")
	@ApiOperation(value = "Modifier une invitation")
	@ResponseBody
	public Invitation modifyInvitation(@RequestBody Invitation invitation) {
		return invitationService.updateInvitation(invitation);
	}
	
	@PutMapping("/expiration-invitation/{invitation-id}")
	@ApiOperation(value = "Expiration de l'invitation")
	@ResponseBody
	public Invitation VerifyExpiration(@PathVariable("invitation-id") Long invitationId) {
		Invitation invitation = invitationService.retrieveInvitation(invitationId);
		return invitationService.VerifyExpirationDate(invitation); 
	}
	
	@PutMapping("/valid-invitation/{invitation-id}/{code-invitation}")
	@ApiOperation(value = "validation de l'invitation")
	@ResponseBody
	public Invitation VerifyIfValid(@PathVariable("invitation-id") Long invitationId,@PathVariable("code-invitation") String code) {
		Invitation invitation = invitationService.retrieveInvitation(invitationId);
		return invitationService.ValidationInvitation(invitation, code);
	}
}

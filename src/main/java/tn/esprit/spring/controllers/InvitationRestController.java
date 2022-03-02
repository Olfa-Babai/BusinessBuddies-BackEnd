package tn.esprit.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import tn.esprit.spring.entities.Invitation;
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
	
	@PostMapping("/add-invitation")
	@ApiOperation(value = "Ajouter une invitation")
	@ResponseBody
	public Invitation addInvitation(@RequestBody Invitation invitation)
	{
		Invitation invit = invitationService.addInvitation(invitation);
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
}

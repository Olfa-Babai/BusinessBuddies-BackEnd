package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name="Invitation")
public class Invitation implements Serializable{
	@Id
	@Column(name="idInvitation")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long idInvitation;
	private String contenu;
	private String codeAcces;
	private typeInvitation typeInvitation;
	private invitationStatus invitationStatus;
	
	@ManyToMany
	private List<Invitation> users;

	public long getIdInvitation() {
		return idInvitation;
	}

	public void setIdInvitation(long idInvitation) {
		this.idInvitation = idInvitation;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public String getCodeAcces() {
		return codeAcces;
	}

	public void setCodeAcces(String codeAcces) {
		this.codeAcces = codeAcces;
	}

	public typeInvitation getTypeInvitation() {
		return typeInvitation;
	}

	public void setTypeInvitation(typeInvitation typeInvitation) {
		this.typeInvitation = typeInvitation;
	}

	public invitationStatus getInvitationStatus() {
		return invitationStatus;
	}

	public void setInvitationStatus(invitationStatus invitationStatus) {
		this.invitationStatus = invitationStatus;
	}

	public List<Invitation> getUsers() {
		return users;
	}

	public void setUsers(List<Invitation> users) {
		this.users = users;
	}
	
	
}

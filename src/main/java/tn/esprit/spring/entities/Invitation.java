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
	private Date dateInvitation;
	
	@ManyToMany
	private List<User> users;
	
	
}

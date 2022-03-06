package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Chat implements Serializable  {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idchat;	
	private int nbmessages;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy="chat")
	private List<Message> messages;

	public int getIdchat() {
		return idchat;
	}

	public void setIdchat(int idchat) {
		this.idchat = idchat;
	}

	public int getNbmessages() {
		return nbmessages;
	}

	public void setNbmessages(int nbmessages) {
		this.nbmessages = nbmessages;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}	
	
	
}
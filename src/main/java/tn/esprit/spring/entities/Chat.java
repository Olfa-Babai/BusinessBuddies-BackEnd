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
}
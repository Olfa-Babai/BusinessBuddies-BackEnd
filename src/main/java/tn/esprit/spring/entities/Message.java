package tn.esprit.spring.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


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
public class Message implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idmessage;
	
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "sender")
    private User sender;

	@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "receiver")
    private User receiver;
	 
	private String body;
	@Enumerated(EnumType.STRING)
	private Status status;
	private LocalDateTime date;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	private Chat chat;
}

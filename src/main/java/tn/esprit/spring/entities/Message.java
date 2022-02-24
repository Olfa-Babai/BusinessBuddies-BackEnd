package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

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
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(style="yyyy-MM-dd")
	private Date date;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	private Chat chat;
}

package tn.esprit.spring.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Feedback implements Serializable {
  @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idF;
  @Column(nullable=false, length=50)
    private String nameF;
	@Column(nullable=false, length=500)
    private String comment;
	@Column
	@Enumerated(EnumType.STRING)
    private FeedbackType type ;
	@Enumerated(EnumType.STRING)
    private FeedBacksKinds kind ;
	@Column 
	private boolean Blocked ;
	@ManyToOne
	User Users;
	
	
	
}


   
	
	
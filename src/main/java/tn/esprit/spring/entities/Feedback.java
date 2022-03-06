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
	public int getIdF() {
		return idF;
	}
	public void setIdF(int idF) {
		this.idF = idF;
	}
	public String getNameF() {
		return nameF;
	}
	public void setNameF(String nameF) {
		this.nameF = nameF;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public FeedbackType getType() {
		return type;
	}
	public void setType(FeedbackType type) {
		this.type = type;
	}
	public FeedBacksKinds getKind() {
		return kind;
	}
	public void setKind(FeedBacksKinds kind) {
		this.kind = kind;
	}
	public boolean isBlocked() {
		return Blocked;
	}
	public void setBlocked(boolean blocked) {
		Blocked = blocked;
	}
	public User getUsers() {
		return Users;
	}
	public void setUsers(User users) {
		Users = users;
	}
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


   
	
	
package tn.esprit.spring.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="Profession")
public class Profession {
	public int getID_Profession() {
		return ID_Profession;
	}
	public void setID_Profession(int iD_Profession) {
		ID_Profession = iD_Profession;
	}
	public String getProfessionName() {
		return ProfessionName;
	}
	public void setProfessionName(String professionName) {
		ProfessionName = professionName;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int ID_Profession;
	@Column
    private String ProfessionName;


	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@OneToOne(mappedBy="profession")
	private User user;
	}
 

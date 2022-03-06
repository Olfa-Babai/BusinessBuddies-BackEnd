package tn.esprit.spring.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

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

	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="profession")
	private List<User> user;
	}
 

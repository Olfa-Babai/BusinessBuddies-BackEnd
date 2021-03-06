package tn.esprit.spring.entities;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="Domain")
public class Domain {
	public int getID_Domain() {
		return ID_Domain;
	}
	public void setID_Domain(int iD_Domain) {
		ID_Domain = iD_Domain;
	}
	public String getDomainName() {
		return DomainName;
	}
	public void setDomainName(String domainName) {
		DomainName = domainName;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int ID_Domain;
	@Column
    private String DomainName;

	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="domain")
	private List<User> user;


	public List<User> getUser() {
		return user;
	}
	public void setUser(List<User> user) {
		this.user = user;
	}




	
	
}

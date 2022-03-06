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
public class Complaint  implements Serializable {
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idC;
    @Column(nullable=false, length=50)
    private String nameC;
    @Column(nullable=false, length=500)
    private String description;
	@Column
	@Enumerated(EnumType.STRING)
    private Processing processing ;
	@Column 
	private boolean Blocked ;
	@ManyToOne
	User Users;
	
	@Override
	public String toString() {
		return "Complaint [idC=" + idC + ", nameC=" + nameC + ", description=" + description + ", processing="
				+ processing + ", Blocked=" + Blocked + ", Users=" + Users + "]";
	}

	public int getIdC() {
		return idC;
	}

	public void setIdC(int idC) {
		this.idC = idC;
	}

	public String getNameC() {
		return nameC;
	}

	public void setNameC(String nameC) {
		this.nameC = nameC;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Processing getProcessing() {
		return processing;
	}

	public void setProcessing(Processing processing) {
		this.processing = processing;
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
	

	
}




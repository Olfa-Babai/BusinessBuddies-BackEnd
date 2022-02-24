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
	@Column
    private String nameC;
	@Column
    private String description;
	@Column
	@Enumerated(EnumType.STRING)
    private Processing processing ;
	@ManyToOne
	User Users;

	
}




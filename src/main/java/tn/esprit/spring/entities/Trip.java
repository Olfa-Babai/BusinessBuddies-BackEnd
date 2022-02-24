package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Trip implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTrip;
	@NotEmpty
	@NotBlank(message="The duree is required")
	private String duree ;
	@NotEmpty
	@NotBlank(message="The trip object is required")
	private String tripobject;
	@Temporal(TemporalType.DATE)
	private Date date;
	@NotEmpty
	@NotBlank(message="The destination is required")
	private String destination;
	
	@OneToMany (mappedBy="trip")
	private List <Program> programs;
	
	@ManyToMany()
	private List <User> users;

}

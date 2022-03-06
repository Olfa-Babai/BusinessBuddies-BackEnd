package tn.esprit.spring.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Followage implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idfollowage;
	@Enumerated(EnumType.STRING)
	private Theme theme;
	private int rating;
	 @JsonIgnore
	 @ManyToOne
	 private User follower;
}

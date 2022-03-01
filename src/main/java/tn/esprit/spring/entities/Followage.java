package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

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
	
	@JoinTable(name = "follows_users",
            joinColumns = { @JoinColumn(name = "idfollowage", referencedColumnName = "idfollowage") },
            inverseJoinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "user_id") }
    )
	 @JsonIgnore
	 @ManyToMany
	 private List<User> followers=new ArrayList<User>();
}

package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idpost;
	private String title;
	private String body;
	private Theme theme;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy="post")
	private List<Like> likes;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy="post")
	private List<Comment> comments;
	
	@JsonIgnore
	@ManyToOne
	private User publisher;
	
}

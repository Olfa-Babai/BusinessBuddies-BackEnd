package tn.esprit.spring.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Table(name="likes")
public class Like implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int idlike;
	TypeLike type;

	@JsonIgnore
	@ManyToOne
	private Post post;
	
	@JsonIgnore
	@ManyToOne
	private User liker;

	public int getIdlike() {
		return idlike;
	}

	public void setIdlike(int idlike) {
		this.idlike = idlike;
	}

	public TypeLike getType() {
		return type;
	}

	public void setType(TypeLike type) {
		this.type = type;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public User getLiker() {
		return liker;
	}

	public void setLiker(User liker) {
		this.liker = liker;
	}
	
	
}

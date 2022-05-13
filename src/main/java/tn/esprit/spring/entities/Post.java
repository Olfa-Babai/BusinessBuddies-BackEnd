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
	
	@ManyToOne
	private User publisher;

	public int getIdpost() {
		return idpost;
	}

	public void setIdpost(int idpost) {
		this.idpost = idpost;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}

	public List<Like> getLikes() {
		return likes;
	}

	public void setLikes(List<Like> likes) {
		this.likes = likes;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public User getPublisher() {
		return publisher;
	}

	public void setPublisher(User publisher) {
		this.publisher = publisher;
	}
	
	
}
